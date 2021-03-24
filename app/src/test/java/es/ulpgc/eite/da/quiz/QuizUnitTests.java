package es.ulpgc.eite.da.quiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.cheat.CheatActivity;
import es.ulpgc.eite.da.quiz.question.QuestionActivity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(RobolectricTestRunner.class)
public class QuizUnitTests {

  ActivityController<QuestionActivity> controller1;
  ActivityController<CheatActivity> controller2;

  TextView question, reply;
  TextView warning, answer;
  Button option1, option2, option3, next, cheat;
  Button yes, no;

  String[] quiz;
  String empty_reply, correct, incorrect;
  String empty_answer, sure;

  @Before
  public void setUp(){

    AppMediator.resetInstance();

    controller1 = Robolectric.buildActivity(QuestionActivity.class);
    controller2 = Robolectric.buildActivity(CheatActivity.class);

    controller1.create().resume().visible();
    updateResources1();

  }


  private void updateResources1() {

    QuestionActivity activity1 = controller1.get();

    quiz = activity1.getResources().getStringArray(R.array.quiz_array);

    empty_reply =activity1.getResources().getString(R.string.empty_reply);
    correct=activity1.getResources().getString(R.string.correct_reply);
    incorrect=activity1.getResources().getString(R.string.incorrect_reply);

    question = activity1.findViewById(R.id.questionTextView);
    reply = activity1.findViewById(R.id.replyTextView);
    option1 = activity1.findViewById(R.id.option1Button);
    option2 = activity1.findViewById(R.id.option2Button);
    option3 = activity1.findViewById(R.id.option3Button);
    next = activity1.findViewById(R.id.nextButton);
    cheat = activity1.findViewById(R.id.cheatButton);
  }


  private void updateResources2() {

    CheatActivity activity2 = controller2.get();

    sure =activity2.getResources().getString(R.string.warning_message);
    empty_answer=activity2.getResources().getString(R.string.empty_answer);

    warning = activity2.findViewById(R.id.warningTextView);
    answer = activity2.findViewById(R.id.answerTextView);
    yes = activity2.findViewById(R.id.yesButton);
    no = activity2.findViewById(R.id.noButton);

  }


  private void rotate1() {

    Bundle bundle = new Bundle();

    controller1
        .saveInstanceState(bundle)
        .pause()
        .stop()
        .destroy();

    controller1 = Robolectric.buildActivity(QuestionActivity.class)
        .create(bundle)
        .restoreInstanceState(bundle)
        .resume()
        .visible();

  }

  private void rotate2() {

    Bundle bundle = new Bundle();

    controller2
        .saveInstanceState(bundle)
        .pause()
        .stop()
        .destroy();

    controller2 = Robolectric.buildActivity(CheatActivity.class)
        .create(bundle)
        .restoreInstanceState(bundle)
        .resume()
        .visible();

  }

  @Test
  public void whenQuestion1_thenCorrect() {

    //  GIVEN 
    //  encontrándonos en pantalla Question después
    //  de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Option correcto
    option3.performClick();

    //  THEN 
    //  mostraremos mensaje Correct ya que la respuesta
    //  del usuario corresponde con respuesta correcta
    //  mostraremos botones Option y Cheat desactivados
    //  mostraremos botón Next activado
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(correct));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(false));
    assertThat(next.isEnabled(), equalTo(true));

  }



  @Test
  public void whenQuestion1Correct_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Question después
    //  de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Option correcto y girar la pantalla
    option3.performClick();
    rotate1();

    //  THEN 
    //  mostraremos mensaje Correct ya que la respuesta
    //  del usuario corresponde con respuesta correcta
    //  mostraremos botones Option y Cheat desactivados
    //  mostraremos botón Next activado
    updateResources1();
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(correct));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(false));
    assertThat(next.isEnabled(), equalTo(true));

  }



  @Test
  public void whenQuestion1_thenIncorrect() {

    //  GIVEN 
    //  encontrándonos en pantalla Question después
    //  de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Option incorrecto
    option2.performClick();

    //  THEN 
    //  mostraremos mensaje Incorrect ya que la respuesta
    //  del usuario corresponde con respuesta incorrecta
    //  mostraremos botón Option desactivado
    //  mostraremos botones Next y Cheat activados
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(incorrect));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(true));
  }


  @Test
  public void whenQuestion1Correct_thenNext() {

    //  GIVEN 
    //  encontrándonos en pantalla Question después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Correct según  la respuesta del usuario
    //  mostraremos botones Option y Cheat desactivados
    //  mostraremos botón Next activado
    option3.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(correct));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(false));
    assertThat(next.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón Next
    next.performClick();

    //  THEN 
    //  mostraremos pantalla Question con siguiente pregunta  ya cargada
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(question.getText().toString(), equalTo(quiz[5]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

  }


  @Test
  public void whenQuestion2_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Question después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Correct según  la respuesta del usuario
    //  mostraremos botones Option y Cheat desactivados
    //  mostraremos botón Next activado
    option3.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(correct));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(false));
    assertThat(next.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón Next y girar la pantalla
    next.performClick();
    rotate1();

    //  THEN 
    //  mostraremos pantalla Question con siguiente pregunta  ya cargada
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    updateResources1();
    assertThat(question.getText().toString(), equalTo(quiz[5]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

  }



  @Test
  public void whenQuestion1Incorrect_thenNext() {

    //  GIVEN 
    //  encontrándonos en pantalla Question después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Incorrect según  la respuesta del usuario
    //  mostraremos botón Option desactivado
    //  mostraremos botones Next y Cheat activados
    option2.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(incorrect));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón Next
    next.performClick();

    //  THEN 
    //  mostraremos pantalla Question con siguiente pregunta  ya cargada
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(question.getText().toString(), equalTo(quiz[5]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));
  }

  @Test
  public void whenQuestion1_thenCheat() {

    //  GIVEN 
    //  encontrándonos en pantalla Question sin haber respondido
    //  a pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Cheat
    cheat.performClick();
    controller2.create().resume().visible();

    //  THEN 
    //  visualizaremos pantalla Cheat donde se nos pedirá confirmación
    //  antes de mostrar respuesta correcta
    //  mostraremos botones Yes y NO activados
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));
  }



  @Test
  public void whenQuestion1Cheated_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Question sin haber respondido
    //  a pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Cheat y girar la pantalla
    cheat.performClick();
    controller2.create().resume().visible();
    rotate1();
    rotate2();

    //  THEN 
    //  visualizaremos pantalla Cheat donde se nos pedirá confirmación
    //  antes de mostrar respuesta correcta
    //  mostraremos botones Yes y NO activados
    updateResources1();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));
  }

  @Test
  public void whenQuestion1Incorrect_thenCheat() {

    //  GIVEN 
    //  encontrándonos en pantalla Question después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Next y Cheat activados
    //  mostraremos botón Option desactivado
    option2.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(incorrect));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón Cheat
    cheat.performClick();
    controller2.create().resume().visible();

    //  THEN 
    //  visualizaremos pantalla Cheat donde se nos pedirá confirmación
    //  antes de mostrar respuesta correcta
    //  mostraremos botones Yes y NO activados
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));
  }



  @Test
  public void whenQuestion1Cheated_thenNo() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón No
    no.performClick();

    //  THEN 
    //  volveremos a pantalla Question
    //  mostraremos pregunta del cuestionario existente
    //  antes de iniciar pantalla Cheat
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));
  }



  @Test
  public void whenQuestion1CheatedNo_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón No y girar la pantalla
    no.performClick();
    rotate1();

    //  THEN 
    //  volveremos a pantalla Question
    //  mostraremos pregunta del cuestionario existente
    //  antes de iniciar pantalla Cheat
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    updateResources1();
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));
  }


  @Test
  public void whenQuestion1IncorrectCheated_thenNo() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat después de responder
    //  a pregunta del cuestionario en pantalla Question
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2.performClick();
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón No
    no.performClick();

    //  THEN 
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  del cuestionario existente antes de iniciar pantalla Cheat
    //  mostraremos botones Next y Cheat activados
    //  mostraremos botón Option desactivado
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(incorrect));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(true));
  }



  @Test
  public void whenQuestion1IncorrectCheatedNo_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat después de responder
    //  a pregunta del cuestionario en pantalla Question
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2.performClick();
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón No y girar la pantalla
    no.performClick();
    rotate1();

    //  THEN 
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  del cuestionario existente antes de iniciar pantalla Cheat
    //  mostraremos botones Next y Cheat activados
    //  mostraremos botón Option desactivado
    updateResources1();
    assertThat(question.getText().toString(), equalTo(quiz[0]));
    assertThat(reply.getText().toString(), equalTo(incorrect));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(true));
  }



  @Test
  public void whenQuestion1Cheated_thenYes() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón Yes
    yes.performClick();

    //  THEN 
    //  visualizaremos respuesta correcta a pregunta
    //  del cuestionario mostrada actualmente en pantalla Question
    //  mostraremos botones Yes y NO desactivados
    updateResources1();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(quiz[3]));
    assertThat(yes.isEnabled(), equalTo(false));
    assertThat(no.isEnabled(), equalTo(false));
  }



  @Test
  public void whenQuestion1CheatedYes_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón Yes y girar la pantalla
    yes.performClick();
    rotate1();
    rotate2();

    //  THEN 
    //  visualizaremos respuesta correcta a pregunta
    //  del cuestionario mostrada actualmente en pantalla Question
    //  mostraremos botones Yes y NO desactivados
    updateResources1();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(quiz[3]));
    assertThat(yes.isEnabled(), equalTo(false));
    assertThat(no.isEnabled(), equalTo(false));
  }


  @Test
  public void whenQuestion1IncorrectCheated_thenYes() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2.performClick();
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));


    //  WHEN 
    //  al pulsar botón Yes
    yes.performClick();

    //  THEN 
    //  visualizaremos respuesta correcta a pregunta
    //  del cuestionario mostrada actualmente en pantalla Question
    //  mostraremos botones Yes y NO desactivados
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(quiz[3]));
    assertThat(yes.isEnabled(), equalTo(false));
    assertThat(no.isEnabled(), equalTo(false));
  }



  @Test
  public void whenQuestion1IncorrectCheatedYes_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2.performClick();
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));


    //  WHEN 
    //  al pulsar botón Yes y girar la pantalla
    yes.performClick();
    rotate1();
    rotate2();

    //  THEN 
    //  visualizaremos respuesta correcta a pregunta
    //  del cuestionario mostrada actualmente en pantalla Question
    //  mostraremos botones Yes y NO desactivados
    updateResources1();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(quiz[3]));
    assertThat(yes.isEnabled(), equalTo(false));
    assertThat(no.isEnabled(), equalTo(false));
  }


  @Test
  public void whenQuestion1Cheated_thenYesAndBack() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón Yes y luego el botón Back
    yes.performClick();
    CheatActivity activity2 = controller2.get();
    activity2.onBackPressed();
    controller1.resume();

    //  THEN 
    //  volveremos a pantalla Question donde mostraremos
    //  pregunta siguiente del cuestionario antes de iniciar pantalla Cheat
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(question.getText().toString(), equalTo(quiz[5]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));
  }




  @Test
  public void whenQuestion1CheatedYesAndBack_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón Yes, luego el botón Back y girar la pantalla
    yes.performClick();
    CheatActivity activity2 = controller2.get();
    activity2.onBackPressed();
    controller1.resume();
    rotate1();

    //  THEN 
    //  volveremos a pantalla Question donde mostraremos
    //  pregunta siguiente del cuestionario antes de iniciar pantalla Cheat
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    updateResources1();
    assertThat(question.getText().toString(), equalTo(quiz[5]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));
  }

  @Test
  public void whenQuestion1IncorrectCheated_thenYesAndBack() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat después
    //  de responder a pregunta del cuestionario en pantalla Question
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2.performClick();
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botón Yes y luego el botón Back
    yes.performClick();
    CheatActivity activity2 = controller2.get();
    activity2.onBackPressed();
    controller1.resume();

    //  THEN 
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  siguiente del cuestionario antes de iniciar pantalla Cheat
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(question.getText().toString(), equalTo(quiz[5]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

  }



  @Test
  public void whenQuestion2_thenCorrect() {

    //  GIVEN 
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    option2.performClick();
    next.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[5]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Option correcto
    option3.performClick();

    //  THEN 
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    assertThat(question.getText().toString(), equalTo(quiz[5]));
    assertThat(reply.getText().toString(), equalTo(correct));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(false));
    assertThat(next.isEnabled(), equalTo(true));
  }



  @Test
  public void whenQuestion2Correct_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    option2.performClick();
    next.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[5]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Option correcto y girar la pantalla
    option3.performClick();
    rotate1();

    //  THEN 
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    updateResources1();
    assertThat(question.getText().toString(), equalTo(quiz[5]));
    assertThat(reply.getText().toString(), equalTo(correct));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(false));
    assertThat(next.isEnabled(), equalTo(true));
  }

  @Test
  public void whenQuestion8_thenCorrect() {

    //  GIVEN 
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[35]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Option correcto
    option1.performClick();

    //  THEN 
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    assertThat(question.getText().toString(), equalTo(quiz[35]));
    assertThat(reply.getText().toString(), equalTo(correct));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(false));
    assertThat(next.isEnabled(), equalTo(true));
  }



  @Test
  public void whenQuestion8Correct_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[35]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Option correcto y girar la pantalla
    option1.performClick();
    rotate1();

    //  THEN 
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    updateResources1();
    assertThat(question.getText().toString(), equalTo(quiz[35]));
    assertThat(reply.getText().toString(), equalTo(correct));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(false));
    assertThat(next.isEnabled(), equalTo(true));
  }

  @Test
  public void whenQuestion10_thenCorrect() {

    //  GIVEN 
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[45]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Option correcto
    option1.performClick();

    //  THEN 
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    assertThat(question.getText().toString(), equalTo(quiz[45]));
    assertThat(reply.getText().toString(), equalTo(correct));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(false));
    assertThat(next.isEnabled(), equalTo(false));
  }


  @Test
  public void whenQuestion10Correct_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[45]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Option correcto y girar la pantalla
    option1.performClick();
    rotate1();

    //  THEN 
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    updateResources1();
    assertThat(question.getText().toString(), equalTo(quiz[45]));
    assertThat(reply.getText().toString(), equalTo(correct));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(false));
    assertThat(next.isEnabled(), equalTo(false));
  }

  @Test
  public void whenQuestion10_thenIncorrect() {

    //  GIVEN 
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    assertThat(question.getText().toString(), equalTo(quiz[45]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(true));
    assertThat(option2.isEnabled(), equalTo(true));
    assertThat(option3.isEnabled(), equalTo(true));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));

    //  WHEN 
    //  al pulsar botón Option incorrecto
    option2.performClick();

    //  THEN 
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Option y Next desactivados
    //  mostraremos botón Cheat activado
    assertThat(question.getText().toString(), equalTo(quiz[45]));
    assertThat(reply.getText().toString(), equalTo(incorrect));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));
  }

  @Test
  public void whenQuestion10Cheated_thenYesAndBack() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat sin responder
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar boton Yes y luego Back
    yes.performClick();
    CheatActivity activity2 = controller2.get();
    activity2.onBackPressed();
    controller1.resume();

    //  THEN 
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  del cuestionario existente antes de iniciar pantalla Cheat
    //  mostraremos botón Cheat activado
    //  mostraremos botones Option y Next desactivados
    assertThat(question.getText().toString(), equalTo(quiz[45]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));
  }


  @Test
  public void whenQuestion10CheatedYesAndBack_thenRotate() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat sin responder
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar boton Yes, luego Back y girar la pantalla
    yes.performClick();
    CheatActivity activity2 = controller2.get();
    activity2.onBackPressed();
    controller1.resume();
    rotate1();

    //  THEN 
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  del cuestionario existente antes de iniciar pantalla Cheat
    //  mostraremos botón Cheat activado
    //  mostraremos botones Option y Next desactivados
    updateResources1();
    assertThat(question.getText().toString(), equalTo(quiz[45]));
    assertThat(reply.getText().toString(), equalTo(empty_reply));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));
  }

  @Test
  public void whenQuestion10IncorrectCheated_thenYesAndBack() {

    //  GIVEN 
    //  encontrándonos en pantalla Cheat después de responder
    //  a pregunta del cuestionario en pantalla Question
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    next.performClick();
    option2.performClick();
    cheat.performClick();
    controller2.create().resume().visible();
    updateResources2();
    assertThat(warning.getText().toString(), equalTo(sure));
    assertThat(answer.getText().toString(), equalTo(empty_answer));
    assertThat(yes.isEnabled(), equalTo(true));
    assertThat(no.isEnabled(), equalTo(true));

    //  WHEN 
    //  al pulsar botones Yes y Back
    yes.performClick();
    CheatActivity activity2 = controller2.get();
    activity2.onBackPressed();
    controller1.resume();

    //  THEN 
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  del cuestionario existente antes de iniciar pantalla Cheat
    //  mostraremos botón Cheat activado
    //  mostraremos botones Next y Option desactivado
    assertThat(question.getText().toString(), equalTo(quiz[45]));
    assertThat(reply.getText().toString(), equalTo(incorrect));
    assertThat(option1.isEnabled(), equalTo(false));
    assertThat(option2.isEnabled(), equalTo(false));
    assertThat(option3.isEnabled(), equalTo(false));
    assertThat(cheat.isEnabled(), equalTo(true));
    assertThat(next.isEnabled(), equalTo(false));
  }


}
