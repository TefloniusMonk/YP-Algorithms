import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CollapseIntervals {
    public static List<Interval> collapseIntervals(List<Interval> intervals){
        if (intervals.size() == 1) return intervals;

        final LinkedList<Interval> sorted = intervals.stream()
                .sorted(Comparator.comparing(Interval::getStart))
                .collect(Collectors.toCollection(LinkedList::new));


        final LinkedList<Interval> result = new LinkedList();
        while(!sorted.isEmpty()){
            final Interval cur = sorted.pollFirst();
            if (sorted.isEmpty()){
                result.add(cur);
                break;
            }
            final Interval next = sorted.pollFirst();
            if (cur.isOverlap(next)){
                cur.merge(next);
                sorted.offerFirst(cur);
            } else {
                sorted.offerFirst(next);
                result.add(cur);
            }
        }
        return result;
    }
}

class Interval {
    private int start;
    private int end;

    public Interval(String str){
        final String[] split = str.split(" ");
        start = Integer.parseInt(split[0]);
        end = Integer.parseInt(split[1]);
    }
    public int getStart() {return this.start;}

    public int getEnd() {return this.end;}

    public String format(){
        return "" + start + " " + end;
    }

    public boolean isOverlap(Interval other){
        if (this.start > other.getStart()) return other.isOverlap(this);

        return this.start <= other.getStart() && this.end >= other.getStart();
    }

    public Interval merge(Interval other){
        this.start = Math.min(this.start, other.getStart());
        this.end = Math.max(this.end, other.getEnd());
        return this;
    }
}