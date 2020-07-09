package binary_search;

/*
*
* 在选举中，第 i 张票是在时间为 times[i] 时投给 persons[i] 的。

现在，我们想要实现下面的查询函数： TopVotedCandidate.q(int t) 将返回在 t 时刻主导选举的候选人的编号。

在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。

示例：

输入：["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
输出：[null,0,1,1,0,0,1]
解释：
时间为 3，票数分布情况是 [0]，编号为 0 的候选人领先。
时间为 12，票数分布情况是 [0,1,1]，编号为 1 的候选人领先。
时间为 25，票数分布情况是 [0,1,1,0,0,1]，编号为 1 的候选人领先（因为最近的投票结果是平局）。
在时间 15、24 和 8 处继续执行 3 个查询。
 

提示：

1 <= persons.length = times.length <= 5000
0 <= persons[i] <= persons.length
times 是严格递增的数组，所有元素都在 [0, 10^9] 范围中。
每个测试用例最多调用 10000 次 TopVotedCandidate.q。
TopVotedCandidate.q(int t) 被调用时总是满足 t >= times[0]
*
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class OnlineElection_m {

/*
* 1. ["TopVotedCandidate","q","q","q","q","q","q"] 表示测试程序中将会进行的操作
*
* 2. [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]] 第一个二维数组中表示分别是
*    persons参数和times参数
*
* 3. [3],[12],[25],[15],[24],[8]参数表示传递给q的参数
*
* 4. q函数的目的是根据传入的时间t，算出来哪个人领先（你可以看到persons数组，一共就两个人0和1）
*
*  */

    class Vote {
        int person, time;
        Vote(int p, int t) {
            person = p;
            time = t;
        }
    }


    // 将leader发生变化及对应的时间存入list
    public List<Vote> A;

    public void topVotedCandidate(int[] persons, int[] times) {
        A = new ArrayList();
        Map<Integer, Integer> count = new HashMap();
        int leader = -1;  // current leader
        int m = 0;  // current number of votes for leader

        for (int i = 0; i < persons.length; ++i) {
            int p = persons[i], t = times[i];
            int c = count.getOrDefault(p, 0) + 1;
            count.put(p, c);

            if (c >= m) {
                if (p != leader) {  // lead change
                    leader = p;
                    A.add(new Vote(leader, t));
                }

                if (c > m) m = c;
            }
        }
    }

    // 利用二分查找
    public int q(int t) {
        int n = A.size();
        int lo = 0;
        int hi = n - 1;

        int res = 0;
        while (lo <= hi) {
            int mi = lo + (hi - lo) / 2;
            if (A.get(mi).time <= t) {
                if ((mi == (n - 1)) || A.get(mi + 1).time > t) res = mi;
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }
        return A.get(res).person;
    }

    public static void main(String[] args) {
        int[] persons =   { 0, 1, 1, 0, 0, 1, 0 };
        int[] times   =   { 0, 5, 10, 15, 20, 25, 30 };
        OnlineElection_m onlineElection_m = new OnlineElection_m();
        onlineElection_m.topVotedCandidate(persons, times);
        System.out.println(onlineElection_m.q(3));
        System.out.println(onlineElection_m.q(12));
        System.out.println(onlineElection_m.q(25));
        System.out.println(onlineElection_m.q(15));
        System.out.println(onlineElection_m.q(24));
        System.out.println(onlineElection_m.q(8));
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
