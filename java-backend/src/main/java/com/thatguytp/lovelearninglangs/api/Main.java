package com.thatguytp.lovelearninglangs.api;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) throws Exception {
        if ("change-this-token-secret".equals(Config.TOKEN_SECRET)) {
            System.out.println("[lovelearninglangs-java-api] WARNING: Using default token secret. Set LLL_TOKEN_SECRET before production deploy.");
        }
        UserService.purgeExpiredUsers();
        ApiServer server = new ApiServer();
        server.start();
    }
}

