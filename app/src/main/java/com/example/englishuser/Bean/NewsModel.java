package com.example.englishuser.Bean;
/*
 * 文件名：NewsModel
 * 作者：created by admin on 2019 五月
 * 描述：
 *
 */

import java.util.List;

public class NewsModel {

    private NewsResponse response;

    public NewsResponse getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "newsResponse=" + response +
                '}';
    }

    public class NewsResponse {
        private String status;
        private String userTier;
        private int total;
        private int startIndex;
        private int pageSize;
        private int currentPage;
        private int pages;
        private String relevance;
        private List<Results> results;

        public String getStatus() {
            return status;
        }

        public String getUserTier() {
            return userTier;
        }

        public int getTotal() {
            return total;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public int getPages() {
            return pages;
        }

        public String getRelevance() {
            return relevance;
        }

        public List<Results> getResults() {
            return results;
        }

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

    public class Results {

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

        public String getId() {
            return id;
        }

        public String getArticle() {
            return article;
        }

        public String getSectionId() {
            return sectionId;
        }

        public String getSectionName() {
            return sectionName;
        }

        public String getWebPublicationDate() {
            return webPublicationDate;
        }

        public String getWebTitle() {
            return webTitle;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public String getApiUrl() {
            return apiUrl;
        }

        public boolean isHosted() {
            return isHosted;
        }

        public String getPillarId() {
            return pillarId;
        }

        public String getPillarName() {
            return pillarName;
        }

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
