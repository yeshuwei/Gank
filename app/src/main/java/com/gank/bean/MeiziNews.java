package com.gank.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.util.ArrayList;

/**
 * Created by 11033 on 2017/3/17.
 */

public class MeiziNews {
    private String error;
    private ArrayList<MeiziNews.Question> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<Question> getResults() {
        return results;
    }

    public void setResults(ArrayList<Question> results) {
        this.results = results;
    }

    @Table("Meizi")public class Question{
        public static final String COL_ID= "_id";
        @PrimaryKey(AssignType.AUTO_INCREMENT)
        private int id;
        @Column(COL_ID)
        private String _id;
        @Column("desc")
        private String desc;

        @Column("createdAt")
        private String createdAt;
        public String getCreatedAt() {
            return createdAt;
        }
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        @Column("type")
        private String type;
        @Column("url")
        private String url;
        @Default("false")
        @Column("mark")
        public boolean mark=false;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
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
