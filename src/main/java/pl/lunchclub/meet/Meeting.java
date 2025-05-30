package pl.lunchclub.meet;

import pl.lunchclub.auth.AuthUuid;

public class Meeting {
    private final MeetingUuid meetingUuid;

    private final AuthUuid owner;
    private final MeetingTime meetingTime;
    private final MeetingPlace meetingPlace;

    private Meeting(
            AuthUuid owner,
            MeetingTime meetingTime,
            MeetingPlace meetingPlace
    ) {
        this.meetingUuid = MeetingUuid.generate();
        this.owner = owner;
        this.meetingTime = meetingTime;
        this.meetingPlace = meetingPlace;
    }
}
