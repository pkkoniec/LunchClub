package pl.lunchclub.common

import pl.lunchclub.UtilSpec
import spock.lang.Unroll

class TimelineSpec extends UtilSpec {

    @Unroll('#input overridden with #override gives #output')
    void 'timelineOverride'() {
        given: 'timeline'
        def inputTimeline = parseTimelines(input)
        def overrideTimeline = parseRange(override)

        expect: 'timeline overridden'
        inputTimeline.override(overrideTimeline) == parseTimelines(output)

        where: 'data'
        input             | override  | output
        ''                | 'A<0,1>'  | ''
        'A<0,10>B<11,20>' | 'C<5,15>' | 'A<0,4>C<5,15>B<16,20>'
        'A<0,10>B<20,30>' | 'C<5,15>' | 'A<0,4>C<5,10>B<20,30>'
    }
}
