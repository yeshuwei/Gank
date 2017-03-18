package com.gank.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * Created by 11033 on 2017/3/18.
 */

public class FrontNews {
    private String error;
    private ArrayList<FrontNews.Question> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<FrontNews.Question> getResults() {
        return results;
    }

    public void setResults(ArrayList<FrontNews.Question> results) {
        this.results = results;
    }

    @Table("Front") public class Question{
        public static final String COL_MARK= "mark";
        @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT)
        private int id;
        @Column("images")
        private ArrayList<String> images;
        @Column("_id")
        private String _id;
        @Column("desc")
        private String desc;
        @Column("type")
        private String type;
        @Column("url")
        private String url;
        @Default("false")
        @Column(COL_MARK)
        public boolean mark=false;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public ArrayList<String> getImages() {
            return images;
        }

        public void setImages(ArrayList<String> images) {
            this.images = images;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}