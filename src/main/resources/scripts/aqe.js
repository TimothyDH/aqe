function QuestionText(props){
    return ({props.question.question_text});
}

var SubmitButton = React.createClass({
    render: function() {
        return (<input type="submit" value="Submit Answer" />);
    }
});

var SimpleMathQuestion = React.createClass({
render: function() {
        return (
        <div>
        <QuestionText question={QUESTION}/>
        </div>
        <div>
        <QuestionAnswer />
        </div>
        <div>
        <SubmitButton />
        </div>
        );
    }
});

var QUESTION = {"question_text":"Two small towns, called Moira and Dollingstown are connected by a long, straight road. You are walking along that road and see a signpost that says Moira is 86.4 away and Dollingstown Dollingstown is 43.7 away. What is the distance between the two towns?","question_answer":130.1,"answer_type":"NUMBER"}

var App = React.createClass({
  super();
    this.state = {
      questions: [{question_text:'loading...'}],
    };

  loadQuestionsFromServer: function () {
    var self = this;
    $.ajax({
      url: "http://localhost:8080/api/2010",
      type: 'get',
      dataType: 'json'
    }).then(function (data) {
      console.log(data);
      self.setState({questions: data});
    });
  },

  getInitialState: function () {
    return {questions: []};
  },

  componentDidMount: function () {
    this.loadQuestionsFromServer();
  },

  render() {
    console.log("+++QUESTIONS+++");
    var questionList = this.state.questions;
    console.log("num QUESTIONS:" + questionList.length);
    questionList.forEach(
            function(item, index, array) {
                console.log(item, index);
       }
    );
    console.log(this.state.questions);
    aquestion = questions[0];
    return ( <QuestionText question={aquestion}/>
    );
  }
});
