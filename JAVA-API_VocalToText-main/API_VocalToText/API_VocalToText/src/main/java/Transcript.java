public class Transcript {
    private String audio_url;
    private String id;
    private String status;
    private String text;

    public String getId(){
        return id;
    }

    public String getAudio_url(){
        return audio_url;}


    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public String getStatus(){
            return status;
        }

    public String getText(){
            return text;
        }
}
