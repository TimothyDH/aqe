package scripts

import java.util.concurrent.ThreadLocalRandom

/**
 * Created by thodkins on 07/09/2017.
 */

def Integer[] percentages = [10, 25, 33, 50, 75]
def String[] things = ["coat", "cat", "hat", "TV", "balloon"]
int full_price_seed = ThreadLocalRandom.current().nextInt(10, 99);
int which_one = ThreadLocalRandom.current().nextInt(0, 4);
Integer percentage = percentages[which_one]

Integer TEN = 10;
println(full_price_seed)
println((full_price_seed/10).intValue()*10)