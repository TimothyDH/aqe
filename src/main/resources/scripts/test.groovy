package scripts

import java.util.concurrent.ThreadLocalRandom

/**
 * Created by thodkins on 07/09/2017.
 */
def engine = new groovy.text.SimpleTemplateEngine()

//Q2
def text = 'The normal price of a $THING is $NORMAL_PRICE pounds. In a sale Clara pays $PER_CENTAGE% of the normal price. How much does she pay for the $THING in the sale?'
def Integer[] percentages = [10, 25, 33, 50, 75]
def String[] things = ["coat", "cat", "hat", "TV", "balloon"]
int full_price_seed = ThreadLocalRandom.current().nextInt(11, 99);
int which_one = ThreadLocalRandom.current().nextInt(0, 4);
Integer percentage = percentages[which_one]
String percentage_string;
String thing = things[which_one]
int answer;
int full_price;
if(percentage == 33){
    percentage_string = "33.3"
    answer = (full_price/3).intValue();
    full_price =  answer * 3;
}
else{
    switch (percentage) {
        case 10:
            full_price = (full_price_seed/10).intValue() * 10
            break
        case 25:
            full_price = (full_price_seed/4).intValue() * 4
            break
        case 50:
            full_price = (full_price_seed/2).intValue() * 2
            break
        case 75:
            full_price = (full_price_seed/3).intValue() * 4
            break
    }
    percentage_string = percentage.toString();
    answer = (full_price/100)*percentage;
}



def binding = ["THING":thing, "NORMAL_PRICE":full_price, "PER_CENTAGE":percentage_string]
def template = engine.createTemplate(text).make(binding)
questiontext =  template.toString();


def question2 = [
        question_type: 'SIMPLE_MATH',
        question_text: questiontext,
        question_answer: answer,
        answer_type: 'NUMBER'
]

println(question2.toString())
