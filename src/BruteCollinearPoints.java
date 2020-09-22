import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints 
{
	private final List<LineSegment> lineSegments;
	
	public BruteCollinearPoints(Point[] points)
	{
		if (!isValid(points)) throw new IllegalArgumentException();
		
		this.lineSegments = new LinkedList<LineSegment>();
		
		Point[] copySegment = points.clone();
        Arrays.sort(copySegment);
		
		for (int i = 0; i < copySegment.length - 3; i++) 
		{
            for (int j = i + 1; j < copySegment.length - 2; j++) 
            {
                double slope12 = copySegment[i].slopeTo(copySegment[j]);
                
                for (int k = j + 1; k < copySegment.length - 1; k++) 
                {
                    double slope13 = copySegment[i].slopeTo(copySegment[k]);
                    
                    if (slope12 == slope13) 
                    {
                        for (int l = k + 1; l < copySegment.length; l++) 
                        {
                            double slope14 = copySegment[i].slopeTo(copySegment[l]);
                           
                            if (slope12 == slope14) 
                            {
                                lineSegments.add(new LineSegment(copySegment[i], copySegment[l]));
                            }
                        }
                    }
                }
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
