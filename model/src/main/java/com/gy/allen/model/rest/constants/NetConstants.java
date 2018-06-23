package com.gy.allen.model.rest.constants;

public class NetConstants {

    //http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.billboard.billList&type=2&size=10&offset=0
    public static final String BASE_URL = "http://tingapi.ting.baidu.com/";
    public static final String URL_PARAM = "v1/restserver/ting";

    //    public static final String BASE_ARTIST_URL = "https://rest.bandsintown.com/";
    //    public static final String ARTIST_URL_PARAM = "artists";
    public static final String BASE_ARTIST_URL = "http://ws.audioscrobbler.com/";
    public static final String ARTIST_URL_PARAM = "2.0";

    //http://gecimi.com/api/lyric/海阔天空/Beyond
    public static final String BASE_LRC_URL = "http://gecimi.com/";
    public static final String LRC_PARAM = "api/lyric";

    public static class Artist {
        public static final String METHOD = "method";
        public static final String API_KEY = "api_key";
        public static final String ARTIST_NAME = "artist";
        public static final String FORMAT = "format";
        public static final String FORMAT_JSON = "json";

        public static final String API_KEY_CONTENT = "488ab9cda4285fbb1bb7dfde5a1b010f";
        public static final String GET_ARTIST_INFO = "artist.getinfo";
    }

    public static class Header {
        public static final String BASE_URL_HEAD = "BaseUrlHead";
        public static final String BAIDU_HEAD_CONTENT = "BaiduApi";
        public static final String BANDSINTOWN_HEAD_CONTENT = "LastFmApi";
        public static final String GECIMI_HEAD_CONTENT = "GeCiMiApi";
        public static final String USER_AGENT = "User-Agent";
        public static final String USER_AGENT_CONTENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";
    }

    public static class Value{
        public static final int TYPE_NEW = 1;
        public static final int TYPE_HOT = 2;
        public static final int TYPE_ROCK = 11;
        public static final int TYPE_JAZZ = 12;
        public static final int TYPE_POPULAR = 16;
        public static final int TYPE_WESTITE = 21;
        public static final int TYPE_CLASSIC = 22;
        public static final int TYPE_LOVE = 23;
        public static final int TYPE_MOVIE = 24;
        public static final int TYPE_NETWORK = 25;
    }

    public static class Param {
        public static final String FORMAT = "format";
        public static final String FROM = "from";
        public static final String METHOD = "method";
        public static final String TYPE = "type";
        public static final String SIZE = "size";
        public static final String OFFSET = "offset";

        public static final String SONG_ID = "songid";

        public static final String SEARCH_QUERY = "query";
    }

    public static class ErrorCode{
        public static final int CODE_SUCCESS = 22000;
    }

}
