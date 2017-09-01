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

def question1 = [        question_text: questiontext,
                         question_answer: answer,
                         answer_type: 'NUMBER'
]

text = 'The normal price of a $THING is $NORMAL_PRICE pounds. In a sale Christine pays $PER_CENTAGE% of the normal price. How much does she pay for the coat in the sale?'

//Q2
def Integer[] percentages = [10, 25, 33, 50, 75]
def String[] things = ["coat", "cat", "car", "TV", "balloon"]
int full_price = ThreadLocalRandom.current().nextInt(10, 99);
int which_one = ThreadLocalRandom.current().nextInt(0, 4);
Integer percentage = percentages[which_one]
String thing = things[which_one]
binding = ["THING":thing, "NORMAL_PRICE":full_price, "PER_CENTAGE":percentage]
template = engine.createTemplate(text).make(binding)
questiontext =  template.toString();

answer = (full_price/100)*percentage;

def question2 = [        question_text: questiontext,
                         question_answer: answer,
                         answer_type: 'NUMBER'
]

//Q4
text = 'A map has the following scale:\n 1 centimetre represents $SCALE kilometres. \n The distance between 2 towns on the map is $DISTANCE cm. What is the actual distance between the 2 towns in kilometres?'
int scale = ThreadLocalRandom.current().nextInt(3, 9);
random1 = ThreadLocalRandom.current().nextDouble(2.5, 9.9);
distance2=new BigDecimal(random1).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
answer = scale * distance2
binding = ["SCALE":scale, "DISTANCE":distance2]
template = engine.createTemplate(text).make(binding)
questiontext =  template.toString();

def question3 = [        question_text: questiontext,
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


def question4 = [        question_text: questiontext,
                         question_answer: [hours: answer_hours, minutes: answer_mins ],
                         answer_type: 'MULTI_PART_NUMBER'
]

//Q5
def cardinals = ["NORTH", "NORTH EAST", "EAST", "SOUTH EAST", "SOUTH", "SOUTH WEST", "WEST", "NORTH WEST"]
which_one = ThreadLocalRandom.current().nextInt(0, 7);
String cardinal = cardinals[which_one]
cardinals.remove(cardinal)
which_one = ThreadLocalRandom.current().nextInt(0, 6);
String new_cardinal = cardinals[which_one]

def directions = ["clockwise", "anti-clockwise"]
which_one = ThreadLocalRandom.current().nextInt(0, 1);
String direction = directions[which_one]
text = 'I am facing $CARDINAL. Through how many degree must I turn $DIRECTION to face $NEW_CARDINAL?.'
binding = ["CARDINAL":cardinal, "DIRECTION":direction, "NEW_CARDINAL": new_cardinal]
template = engine.createTemplate(text).make(binding)
questiontext =  template.toString();

def question5 = [        question_text: questiontext,
                         question_answer: answer,
                         answer_type: 'NUMBER'
]

def json = JsonOutput.toJson([question1, question2, question3, question4, question5])
JsonOutput.prettyPrint(json)
return json.toString()


