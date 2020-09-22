import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints 
{
	private final List<LineSegment> lineSegments;
	
	public FastCollinearPoints(Point[] points)
	{
		if (!isValid(points)) throw new IllegalArgumentException();
		
		lineSegments = new LinkedList<LineSegment>();
		
		Point[] copySegment = points.clone();
        Arrays.sort(copySegment);
		
        for (int i = 0; i < copySegment.length; i++) 
        {
            Point p = copySegment[i];
            Point[] pointsBySlope = copySegment.clone();
            Arrays.sort(pointsBySlope, p.slopeOrder());

            for (int j = 0, k = 1, l = 2; l < pointsBySlope.length; l++) 
            {
                while (l < pointsBySlope.length
                        && pointsBySlope[j].compareTo(pointsBySlope[k]) == 0 
                        && pointsBySlope[j].compareTo(pointsBySlope[l]) == 0) 
                {
                	++l;
                }

                if (l - k >= 3 && pointsBySlope[j].compareTo(pointsBySlope[k]) < 0) 
                {
                	lineSegments.add(new LineSegment(pointsBySlope[j], pointsBySlope[l - 1]));
                }

                k = l;
            }
        }
	}

	private boolean isValid(Point[] points) 
	{
		if (points == null) return true;
		
		for (int i = 0; i < points.length - 1; i++) 
		{
			if (points[i] == null) return true;
			
			 if (points[i].compareTo(points[i + 1]) == 0) 
	         {
	                return true;
	         }
		}
		
		return false;
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
