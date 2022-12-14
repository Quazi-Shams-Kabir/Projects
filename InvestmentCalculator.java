import java.util.Scanner;

public class InvestmentCalculator {
    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);

        int deposit,midCap,smallCap,largeCap,multiCap;

        System.out.println("\t\t\t\t~~Enter the amounts invested already in a particular fund, Enter 0 if not invested~~");
        System.out.print("Large Cap: ");
        largeCap=in.nextInt();
        System.out.print("Mid Cap:   ");
        midCap=in.nextInt();
        System.out.print("Small Cap: ");
        smallCap=in.nextInt();
        System.out.print("Multi Cap: ");
        multiCap=in.nextInt();

        //Dividing multiCap into respective ratio
        largeCap+=multiCap/2;
        midCap+=multiCap/4;
        smallCap+=multiCap/4;
        int total=largeCap+midCap+smallCap;

        //Calculate and show the current percentage of asset allocation
        float presentPercentageLargeCap=percentage(largeCap,total);
        float presentPercentageMidCap=percentage(midCap,total);
        float presentPercentageSmallCap=100-(presentPercentageLargeCap+presentPercentageMidCap);
        System.out.printf("\nCurrent asset allocation percentage of:- \nLarge Cap %.2f%%\nMid Cap %.2f%%\nSmall Cap %.2f%% \n\n",presentPercentageLargeCap,presentPercentageMidCap,presentPercentageSmallCap);

        System.out.print("Enter the amount you want to deposit: ");
        deposit=in.nextInt();
        total+=deposit;

        //Returns the total amount of money it needed make it according to their ratio
        int newMidCap=binary_search(midCap,deposit,total,45,47);
        int newSmallCap=binary_search(smallCap,deposit-(newMidCap-midCap),total,20,22);
        int newLargeCap=total-(newMidCap+newSmallCap);

        //Calculate the new percentage
        float newPercentageMidCap=percentage(newMidCap,total);
        float newPercentageSmallCap=percentage(newSmallCap,total);
        float newPercentageLargeCap=100-(newPercentageMidCap+newPercentageSmallCap);

        //Output the amount of money from future deposit it needed to match the ratio
        System.out.printf("\nInvest %d to make it %.2f%% for Large Cap\n",newLargeCap-largeCap,newPercentageLargeCap);
        System.out.printf("Invest %d to make it %.2f%% for Mid Cap\n",newMidCap-midCap,newPercentageMidCap);
        System.out.printf("Invest %d to make it %.2f%% for Small Cap\n",newSmallCap-smallCap,newPercentageSmallCap);
    }
    static float percentage(int fund,int total){
        float ans=0;
        if (fund!=0){
        ans = (100.0f/total)*fund;
        }
        return ans;
    }

    //calculate the total amount of money should be there to match lowerRange and upperRange
    static int binary_search(int fund,int deposit,int total,int lowerRange,int upperRange){
        int start = fund;
        int end = fund+deposit;

        while (start<end){
            int mid = start+(end-start)/2;
            //Calculate and check the new percentage with upper and lower range of the particular fund
            float pMid=percentage(mid,total);
            if (pMid>=lowerRange && pMid<=upperRange){
                return mid;
            }
            if (pMid<lowerRange){
                start=mid+100;
            }
            else end = mid-100;
        }
        return start;
    }
}
