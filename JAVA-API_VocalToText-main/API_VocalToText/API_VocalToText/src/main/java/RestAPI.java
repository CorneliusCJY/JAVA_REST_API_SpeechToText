import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class RestAPI {

    public static void main(String[] args) throws Exception{

        Transcript transcript = new Transcript();
        transcript.setAudio_url("https://github.com/SergeyKovalevDev/JavaAPITutorial/raw/main/Thirsty.mp4");
        Gson gson= new Gson();
        String jsonRequest = gson.toJson(transcript);

        System.out.println(jsonRequest);

        // Post Request
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", "5d697fe6ebbf409c85282650775058a8" )
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();



        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());

        System.out.println(postResponse.body());

        transcript = gson.fromJson(postResponse.body(), Transcript.class);

        transcript.getId();

        // Get request
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"+ transcript.getId()))
                .header("Authorization","5d697fe6ebbf409c85282650775058a8" )
                .build();

        while(true) {
            HttpResponse<String> getResponse = httpClient.send(postRequest, BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);

            System.out.println(transcript.getStatus());

            if  ("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus()))
                break;
        }
        Thread.sleep(1000);
        System.out.println("Transcription completed!");
        System.out.println(transcript.getText());
    }
}
