import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';

var QuestionText = React.createClass({
  render: function() {
    return (<div>{this.props.question.question_text}</div>);
  }
});

var QuestionAnswer = React.createClass({
  render: function() {
    return (<div><input></div>);
  }
});


var QUESTION = {"question_text":"Two small towns, called Moira and Dollingstown are connected by a long, straight road. You are walking along that road and see a signpost that says Moira is 86.4 away and Dollingstown Dollingstown is 43.7 away. What is the distance between the two towns?","question_answer":130.1,"answer_type":"NUMBER"}
ReactDOM.render(
  <QuestionText question={QUESTION}/>, document.getElementById('root')
);

