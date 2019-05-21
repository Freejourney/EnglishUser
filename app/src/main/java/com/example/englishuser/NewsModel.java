package com.example.englishuser;
/*
 * 文件名：NewsModel
 * 作者：created by admin on 2019 五月
 * 描述：
 *
 */

import java.util.List;

public class NewsModel {

    private NewsResponse response;

    @Override
    public String toString() {
        return "NewsModel{" +
                "newsResponse=" + response +
                '}';
    }

    private class NewsResponse {
        private String status;
        private String userTier;
        private int total;
        private int startIndex;
        private int pageSize;
        private int currentPage;
        private int pages;
        private String relevance;
        private List<Results> results;

        @Override
        public String toString() {
            return "NewsModel{" +
                    "status='" + status + '\'' +
                    ", userTier='" + userTier + '\'' +
                    ", total=" + total +
                    ", startIndex=" + startIndex +
                    ", pageSize=" + pageSize +
                    ", currentPage=" + currentPage +
                    ", pages=" + pages +
                    ", relevance='" + relevance + '\'' +
                    ", results=" + results +
                    '}';
        }
    }

    private class Results {

        private String id;
        private String article;
        private String sectionId;
        private String sectionName;
        private String webPublicationDate;
        private String webTitle;
        private String webUrl;
        private String apiUrl;
        private boolean isHosted;
        private String pillarId;
        private String pillarName;

        @Override
        public String toString() {
            return "Results{" +
                    "id='" + id + '\'' +
                    ", article='" + article + '\'' +
                    ", sectionId='" + sectionId + '\'' +
                    ", sectionName='" + sectionName + '\'' +
                    ", webPublicationDate='" + webPublicationDate + '\'' +
                    ", webTitle='" + webTitle + '\'' +
                    ", webUrl='" + webUrl + '\'' +
                    ", apiUrl='" + apiUrl + '\'' +
                    ", isHosted=" + isHosted +
                    ", pillarId='" + pillarId + '\'' +
                    ", pillarName='" + pillarName + '\'' +
                    '}';
        }
    }


}
