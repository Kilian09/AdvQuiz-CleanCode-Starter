package es.ulpgc.eite.da.quiz.question;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.quiz.app.QuestionToCheatState;

public class QuestionPresenter implements QuestionContract.Presenter {

    public static String TAG = QuestionPresenter.class.getSimpleName();

    private AppMediator mediator;
    private WeakReference<QuestionContract.View> view;
    private QuestionState state;
    private QuestionContract.Model model;

    public QuestionPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getQuestionState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");

        // call the model
        state.question = model.getQuestion();
        state.option1 = model.getOption1();
        state.option2 = model.getOption2();
        state.option3 = model.getOption3();

        // reset state to tests
        state.answerCheated = false;
        state.optionClicked = false;
        state.option = 0;

        // update the view
        disableNextButton();
        view.get().resetReply();
    }


    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");

        model.setQuizIndex(state.quizIndex);
        state.question = model.getQuestion();
        state.option1 = model.getOption1();
        state.option1 = model.getOption1();
        state.option2 = model.getOption2();

        if (state.optionClicked) {
            boolean isCorrect = model.isCorrectOption(state.option);
            view.get().updateReply(isCorrect);
        } else {
            view.get().resetReply();
        }
    }


    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");

        // use passed state if is necessary
        CheatToQuestionState savedState = getStateFromCheatScreen();
        if (savedState != null) {

            // fetch the model
            if (savedState.answerCheated) {
                state.optionEnabled = false;
                if (!model.hasQuizFinished()) {
                    onNextButtonClicked();
                }
            }
        }
        // update the view
        view.get().displayQuestion(state);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy()");
    }

    @Override
    public void onOptionButtonClicked(int option) {
        Log.e(TAG, "onOptionButtonClicked()");

        state.optionClicked = true;
        state.option = option;

        boolean isCorrect = model.isCorrectOption(option);

        if (isCorrect) {
            state.cheatEnabled = false;
        } else {
            state.cheatEnabled = true;
        }

        enableNextButton();
        view.get().updateReply(isCorrect);
        view.get().displayQuestion(state);
    }

    @Override
    public void onNextButtonClicked() {
        Log.e(TAG, "onNextButtonClicked()");

        state.quizIndex = state.quizIndex + 5;
        model.updateQuizIndex();

        state.answerCheated = false;
        state.optionClicked = false;
        state.option = 0;

        state.question = model.getQuestion();
        state.option1 = model.getOption1();
        state.option2 = model.getOption2();
        state.option3 = model.getOption3();

        disableNextButton();

        view.get().displayQuestion(state);
        view.get().resetReply();
    }

    @Override
    public void onCheatButtonClicked() {
        Log.e(TAG, "onCheatButtonClicked()");

        String answer = model.getAnswer();
        QuestionToCheatState newState = new QuestionToCheatState(answer);
        passStateToCheatScreen(newState);
        view.get().navigateToCheatScreen();

    }

    private void passStateToCheatScreen(QuestionToCheatState state) {
        mediator.setQuestionToCheatState(state);
    }

    private CheatToQuestionState getStateFromCheatScreen() {

        return mediator.getCheatToQuestionState();
    }

    private void disableNextButton() {
        state.optionEnabled = true;
        state.cheatEnabled = true;
        state.nextEnabled = false;

    }

    private void enableNextButton() {
        state.optionEnabled = false;

        if (!model.hasQuizFinished()) {
            state.nextEnabled = true;
        }
    }

    @Override
    public void injectView(WeakReference<QuestionContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(QuestionContract.Model model) {
        this.model = model;
    }

}
