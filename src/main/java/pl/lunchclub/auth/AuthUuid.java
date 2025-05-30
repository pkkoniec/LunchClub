package pl.lunchclub.auth;

import java.util.UUID;

public record AuthUuid(UUID uuid) {
    public static AuthUuid generate() {
        return new AuthUuid(UUID.randomUUID());
    }
}
