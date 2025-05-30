package pl.lunchclub.meet;

import java.util.UUID;

record MeetingUuid(UUID uuid) {
    static MeetingUuid generate() {
        return new MeetingUuid(UUID.randomUUID());
    }
}
