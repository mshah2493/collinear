import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints 
{
	private final List<LineSegment> lineSegments;
	
	public FastCollinearPoints(Point[] points)
	{
		if (hasEmptyPoint(points)) throw new IllegalArgumentException();
		
		lineSegments = new LinkedList<LineSegment>();
		
		Point[] copySegment = points.clone();
        Arrays.sort(copySegment);
        
        if (!hasDuplicates(copySegment)) throw new IllegalArgumentException();
		
        for (int i = 0; i < copySegment.length - 3; i++) 
        {
            Arrays.sort(copySegment);
            Arrays.sort(copySegment, copySegment[i].slopeOrder());

            for (int p = 0, j = 1, k = 2; k < copySegment.length; k++) 
            {
                while (k < copySegment.length
                        && Double.compare(copySegment[p].slopeTo(copySegment[j]), copySegment[p].slopeTo(copySegment[k])) == 0) 
                {
                    k++;
                }
                
                if (k - j >= 3 && copySegment[p].compareTo(copySegment[j]) < 0) 
                {
                	lineSegments.add(new LineSegment(copySegment[p], copySegment[k - 1]));
                }

                j = k;
            }
        }
	}

	private boolean hasEmptyPoint(Point[] points) 
	{
        if (points == null) return true;
        
        for (Point p : points) 
            if (p == null) return true;
        
        return false;
    }
	
	private boolean hasDuplicates(Point[] points) 
	{
		for (int i = 0; i < points.length - 1; i++) 
		{			
			 if (points[i].compareTo(points[i + 1]) == 0) return false;
		}
		
		return true;
	}

	public int numberOfSegments()
	{
		return lineSegments.size();
	}
	
	public LineSegment[] segments()
	{
		return lineSegments.toArray(new LineSegment[lineSegments.size()]); 
	}
}
