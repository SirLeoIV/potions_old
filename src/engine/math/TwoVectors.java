package engine.math;

import engine.dto.Point2D;
import engine.dto.TwoPoints2D;

/*
* This class represents the relationship between two 2D
* vectors in a coordinate system.
*/
public class TwoVectors {

    /*
    *  a1 and a2 are the orientation of the first vector
    *  b1 and b2 are the orientation of the second vector
    *  d1 and d2 are the differences of the starting-points of the vectors
    */
    double a1, b1, d1, a2, b2, d2;

    public TwoVectors(double a1, double b1, double d1, double a2, double b2, double d2) {
        this.a1 = a1;
        this.b1 = b1;
        this.d1 = d1;
        this.a2 = a2;
        this.b2 = b2;
        this.d2 = d2;
    }

    /*
    * point 1 is the starting point
    * point 2 is used to calculate the orientaion of the vector
    */
    public TwoVectors(Point2D a1, Point2D a2, Point2D b1, Point2D b2) {
        initVectors(a1, a2, b1, b2);
    }

    private void initVectors(Point2D a1, Point2D a2, Point2D b1, Point2D b2) {
        Point2D orientationA = new Point2D((a2.getX() - a1.getX()), (a2.getY() - a1.getY()));
        Point2D orientationB = new Point2D(-(b2.getX() - b1.getX()), -(b2.getY() - b1.getY()));

        this.a1 = orientationA.getX();
        this.b1 = orientationB.getX();
        d1 = b1.getX() - a1.getX();
        this.a2 = orientationA.getY();
        this.b2 = orientationB.getY();
        d2 = b1.getY() - a1.getY();
    }

    public TwoVectors(TwoPoints2D a, TwoPoints2D b) {
        initVectors(a.getPoint1(), a.getPoint2(), b.getPoint1(), b.getPoint2());
    }

    public TwoVectors() {
        a1 = 0;
        b1 = -4;
        d1 = -1;
        a2 = 4;
        b2 = 0;
        d2 = 2;
    }

    public boolean calculateCollision() {

     /* System.out.println();
        System.out.println();
        System.out.println(a1);
        System.out.println(b1);
        System.out.println(d1);
        System.out.println(a2);
        System.out.println(b2);
        System.out.println(d2); */

        int n = 2;
        double [][]mat = new double[n][n];
        double [][]constants = new double[n][1];

        mat[0][0] = a1;
        mat[0][1] = b1;
        mat[1][0] = a2;
        mat[1][1] = b2;
        constants[0][0] = d1;
        constants[1][0] = d2;

        //inverse of matrix mat[][]
        double[][] inverted_mat = invert(mat);

        //Multiplication of mat inverse and constants
        double[][] result = new double[n][1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] = result[i][j] + (inverted_mat[i][k] * constants[k][j]);
                }
            }
        }
//        System.out.println("The product is:");
//        for(int i=0; i<n; i++) {
//            System.out.println(result[i][0] + " ");
//        }

        return (result[0][0] >= 0
                && result[0][0] <=1
                && result[1][0] >= 0
                && result[1][0] <= 1);

    }

    public static double[][] invert(double[][] a) {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i]*b[index[i]][k];

        // Perform backward substitutions
        for (int i=0; i<n; ++i) {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.

    public static void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];

        // Initialize the index
        for (int i=0; i<n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) {
            double c1 = 0;
            for (int j=0; j<n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) {
            double pi1 = 0;
            for (int i=j; i<n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) {
                double pj = a[index[i]][j]/a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other engine.elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }
}
