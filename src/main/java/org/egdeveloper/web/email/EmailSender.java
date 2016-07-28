package org.egdeveloper.web.email;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.egdeveloper.data.model.Confident;
import org.egdeveloper.data.model.GPSPosition;
import org.egdeveloper.data.model.HealthStat;
import org.egdeveloper.data.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import static j2html.TagCreator.*;

@Component
public class EmailSender implements IHealthAlarmSender{

    @Value("${mail.smtp.auth}")
    private String smtp_auth;

    @Value("${mail.smtp.starttls.enable}")
    private String smtp_starttls_enable;

    @Value("${mail.smtp.host}")
    private String smtp_host;

    @Value("${mail.smtp.port}")
    private String smtp_port;

    @Value("${mail.debug}")
    private String mail_debug;

    @Value("${google.maps.connect.url}")
    private String google_maps_url;

    @Value("${google.app.key}")
    private String app_key;

    @Value("${google.geocode.connect.url}")
    private String geocode_url;

    @Value("${google.app.server.key}")
    private String server_key;

    public void sendAlarm(User user, HealthStat healthStat){

        Properties props = new Properties();
        props.put("mail.smtp.auth", smtp_auth);
        props.put("mail.smtp.starttls.enable", smtp_starttls_enable);
        int dog_sign = user.getEmail().indexOf("@");
        String host = "smtp." + user.getEmail().substring(dog_sign + 1, user.getEmail().length());
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", smtp_port);
        props.put("mail.debug", mail_debug);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user.getEmail(), user.getPassword());
                    }
                });
        try {
            for(Confident confident : user.getConfidents()){
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user.getEmail()));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(confident.getEmail()));
                message.setSubject("Please help me!");
                message.setContent(generateMessage(user, confident, healthStat), "text/html; charset=utf-8");
                Transport.send(message);
            }
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateMessage(User user, Confident confident, HealthStat healthStat){
        return html().with(
                head().with(
                        title("Please help me!")
                ),
                body().with(
                        h2("I now have had a heart attack. Please help me, " + confident.getName() + "!"),
                        br(),
                        h3("My heart rate is " + healthStat.getHeartRate() + "bpm."),
                        h3("You can find me at " + String.format("(%f, %f)", healthStat.getGpsPosition().getLatitude(), healthStat.getGpsPosition().getLongitude())),
//                        iframe().withSrc(String.format("http://maps.google.com/maps/api/staticmap?center=%f,%f&zoom=12&size=400x400&sensor=false&key=%s", healthStat.getGpsPosition().getLatitude(), healthStat.getGpsPosition().getLongitude(), server_key)).attr("width", "100%").attr("height", "100%")
                        a("address").withHref("")
                )
        ).render();
    }

    private String resolveAddress(GPSPosition gps){
        try(CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpGet req = new HttpGet(String.format("https://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f&key=%s", gps.getLatitude(), gps.getLongitude(), app_key));
            HttpResponse resp = client.execute(req);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
            String str;
            StringBuilder json = new StringBuilder("");
            while((str = reader.readLine()) != null){
                json.append(str);
            }
            System.out.println(json);
            JSONObject jObject = new JSONObject(json.toString());
            JSONArray results = jObject.getJSONArray("results");
            JSONObject resObject = new JSONObject(results.get(0).toString());
            return resObject.getString("formatted_address");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
