package com.gy.allen.model.response;

import java.util.List;

/**
 * Created by allen on 18/2/26.
 */

public class DailyGankResp {

    /**
     * error : false
     * results : [{"_id":"5732b15067765974f885c05a","content":"sdf ","publishedAt":"2016-05-11T12:11:00.0Z","title":"秒拍牛人大集合，看到哪个你跪了"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5732b15067765974f885c05a
         * content : sdf
         * publishedAt : 2016-05-11T12:11:00.0Z
         * title : 秒拍牛人大集合，看到哪个你跪了
         */

        private String _id;
        private String content;
        private String publishedAt;
        private String title;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
