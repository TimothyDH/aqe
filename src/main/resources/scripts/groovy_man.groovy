package scripts

//import groovy.json.JsonBuilder
import groovy.json.JsonOutput

import java.util.concurrent.ThreadLocalRandom

/**
 * Created by thodkins on 28/08/2017.
 */

def text = 'Two small towns, called Moira and Dollingstown are connected by a long, straight road. You are walking along that road and see a signpost that says Moira is $DISTANCE1 away and Dollingstown Dollingstown is $DISTANCE2 away. What is the distance between the two towns?'
double random1 = ThreadLocalRandom.current().nextDouble(10, 99);
double random2 = ThreadLocalRandom.current().nextDouble(10, 99);
def distance1=new BigDecimal(random1).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
def distance2=new BigDecimal(random2).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
def answer = new BigDecimal(distance1 + distance2).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
def binding = ["DISTANCE1":distance1, "DISTANCE2":distance2]
def engine = new groovy.text.SimpleTemplateEngine()
def template = engine.createTemplate(text).make(binding)
String questiontext =  template.toString();

def question1 = [
                         question_type: 'SIMPLE_MATH',
                         question_text: questiontext,
                         question_answer: answer,
                         answer_type: 'NUMBER'
]

//Q2
text = 'The normal price of a $THING is $NORMAL_PRICE pounds. In a sale Clara pays $PER_CENTAGE% of the normal price. How much does she pay for the $THING in the sale?'
def Integer[] percentages = [10, 25, 33, 50, 75]
def String[] things = ["coat", "cat", "hat", "TV", "balloon"]
int full_price = ThreadLocalRandom.current().nextInt(10, 99);
int which_one = ThreadLocalRandom.current().nextInt(0, 4);
Integer percentage = percentages[which_one]
String percentage_string;
String thing = things[which_one]
//percentage=33
if(percentage == 33){
    percentage_string = "33.3"
    answer = (full_price/3).intValue()
    full_price = answer * 3
}
else{
    percentage_string = percentage.toString();
    answer = (full_price/100)*percentage;
}

binding = ["THING":thing, "NORMAL_PRICE":full_price, "PER_CENTAGE":percentage_string]
template = engine.createTemplate(text).make(binding)
questiontext =  template.toString();


def question2 = [
                         question_type: 'SIMPLE_MATH',
                         question_text: questiontext,
                         question_answer: answer,
                         answer_type: 'NUMBER'
]

//Q4
text = 'A map has the following scale:\n 1 centimetre represents $SCALE kilometres. \n The distance between 2 towns on the map is $DISTANCE cm. What is the actual distance between the 2 towns in kilometres?'
int scale = ThreadLocalRandom.current().nextInt(3, 9);
random1 = ThreadLocalRandom.current().nextDouble(2.5, 9.9);
distance2=new BigDecimal(random1).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
answer = new BigDecimal(scale * distance2).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
binding = ["SCALE":scale, "DISTANCE":distance2]
template = engine.createTemplate(text).make(binding)
questiontext =  template.toString();

def question3 = [
                         question_type: 'SIMPLE_MATH',
                         question_text: questiontext,
                         question_answer: answer,
                         answer_type: 'NUMBER'
]

//Q4
text = 'Write $MINUTES minutes in hours and minutes.'
int minutes = ThreadLocalRandom.current().nextInt(101, 799);
binding = ["MINUTES":minutes]
template = engine.createTemplate(text).make(binding)
questiontext =  template.toString();

int answer_hours = minutes/60
int answer_mins = minutes%60


def question4 = [
                         question_type: 'MULTI_PART_ANSWER_MATH',
                         question_text: questiontext,
                         question_answer: [hours: answer_hours, minutes: answer_mins ],
                         answer_type: 'MULTI_PART_NUMBER_TIME'
]

//Q5
def cardinals = ["NORTH", "NORTH EAST", "EAST", "SOUTH EAST", "SOUTH", "SOUTH WEST", "WEST", "NORTH WEST"]
def cloned_cardinals = cardinals.clone();
which_one = ThreadLocalRandom.current().nextInt(0, 7);
String cardinal = cardinals[which_one]
cardinals.remove(cardinal)
next_one = ThreadLocalRandom.current().nextInt(0, 6);
String new_cardinal = cardinals[next_one]

int start_index = cloned_cardinals.findIndexOf {it -> it == cardinal}
int end_index = cloned_cardinals.findIndexOf {it -> it == new_cardinal}
println("start_index " + start_index)
println("end_index " + end_index)

def directions = ["clockwise", "anti-clockwise"]
which_one = ThreadLocalRandom.current().nextInt(0, 1);
String direction = directions[which_one]
int difference;
if(direction=="clockwise"){
    if(end_index > start_index){
        println("bigger- ")
        difference = (end_index - start_index) * 45;
        println("difference- " + difference)
    }
    else{
        println("smaller- ")
        difference = end_index;
        difference += (cloned_cardinals.size())-start_index;
        difference = difference * 45
        println("difference- " + difference)
    }
}
else{
    if(end_index < start_index){
        println("small- ")
        difference = (start_index - end_index) * 45;
        println("difference- " + difference)
    }
    else{
        println("bigg- ")
        difference = (cloned_cardinals.size())-start_index;
        difference += end_index;
        difference = difference * 45
        println("difference- " + difference)
    }
}
text = 'I am facing $CARDINAL. Through how many degrees must I turn $DIRECTION to face $NEW_CARDINAL?.'
binding = ["CARDINAL":cardinal, "DIRECTION":direction, "NEW_CARDINAL": new_cardinal]
template = engine.createTemplate(text).make(binding)
questiontext =  template.toString();

def question5 = [
                         question_type: 'SIMPLE_MATH',
                         question_text: questiontext,
                         question_answer: difference,
                         answer_type: 'NUMBER'
]

def json = JsonOutput.toJson([question1, question2, question3, question4, question5])
println(JsonOutput.prettyPrint(json));
return json.toString()


