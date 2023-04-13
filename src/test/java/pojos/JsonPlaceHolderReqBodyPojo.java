package pojos;

public class JsonPlaceHolderReqBodyPojo {

    // 1- Tum key degerlerini class levelda aldiklari data turune gore private variable olarak olustur.

    /*
    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
     */

    private String title;
    private String body;
    private int userId;
    private int id;

    // 2- tum variablelar icin getter() - setter() lari olustur


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 3- tum variablelari iceren parametreli constructor olustur

    public JsonPlaceHolderReqBodyPojo(String title, String body, int userId, int id) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }
    // 4 - parametreli cons. olusturdugum icin bir tane de parametresiz cons. olusturuyoruz


    public JsonPlaceHolderReqBodyPojo() {
    }
    // 5 - toString() olusturalim

    @Override
    public String toString() {
        return "JsonPlaceHolderReqBodyPojo{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
