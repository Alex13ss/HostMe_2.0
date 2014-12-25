package com.softserve.edu.utils;

public class Search {

    private String name;
    private String value;

    private Search() {

    }

    private Search(SearchBuilder builder) {
        this.name = builder.key;
        this.value = builder.value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static class SearchBuilder {

        private String key;
        private String value;

        public static void create(){
            new Search();
        }

        public SearchBuilder() {

        }

        public SearchBuilder setKey(String key) {
            this.key = key;
            return this;
        }

        public SearchBuilder setValue(String value) {
            this.value = value;
            return this;
        }

        public Search build() {
            return new Search(this);
        }

    }
}
