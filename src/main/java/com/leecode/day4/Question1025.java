package com.leecode.day4;
/**
 * @Author: hataki
 * @Date: 2020/7/20
 * Time: 12:04
 * description: 1025. Divisor Game
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
 *
 * Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also, if a player cannot make a move, they lose the game.
 *
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 *
 *  
 *
 * Example 1:
 *
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 * Example 2:
 *
 * Input: 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divisor-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question1025 {

    public static void main(String[] args) {

    }

    /**
     * 推理：
     *  当N为奇数时，alice只能拿1 ，偶数必胜；
     *  当N为偶数时，alice拿2 ，则bob 只能拿1 ，alice必败
     *  。。。。。。。
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {


        return N % 2 == 0 ;
    }
}
