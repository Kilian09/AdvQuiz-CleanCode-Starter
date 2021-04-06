package es.ulpgc.eite.da.quiz.cheat;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.quiz.app.QuestionToCheatState;

public class CheatPresenter implements CheatContract.Presenter {

    public static String TAG = CheatPresenter.class.getSimpleName();

    private AppMediator mediator;
    private WeakReference<CheatContract.View> view;
    private CheatState state;
    private CheatContract.Model model;

    public CheatPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getCheatState();
    }


    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");

        // reset state to tests
        state.answerEnabled = true;
        state.answerCheated = false;
        state.answer = null;

        // update the view
        view.get().resetAnswer();
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");

        if (state.answerCheated) {
            // update the view
            view.get().displayAnswer(state);
        } else {
            // update the view
            view.get().resetAnswer();
        }
    }


    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");

        // use passed state if is necessary
        QuestionToCheatState savedState = getStateFromQuestionScreen();
        if (savedState != null) {

            // fetch the model
            model.setAnswer(savedState.answer);
        }
    }
/*
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy()");

        // Reset current state in Mediator

    }
*/
    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed()");

        CheatToQuestionState newState = new CheatToQuestionState(state.answerCheated);
        passStateToQuestionScreen(newState);
        view.get().onFinish();
    }

    @Override
    public void onWarningButtonClicked(int option) {
        Log.e(TAG, "onWarningButtonClicked()");

        //option=1 => yes, option=0 => no

        if (option == 1) {
            state.answerCheated = true;
            state.answerEnabled = false;
            state.answer = model.getAnswer();

            view.get().displayAnswer(state);
        } else if (option == 0) {
            view.get().onFinish();
        }
    }


    private void passStateToQuestionScreen(CheatToQuestionState state) {
        mediator.setCheatToQuestionState(state);
    }

    private QuestionToCheatState getStateFromQuestionScreen() {
        return mediator.getQuestionToCheatState();
    }

    @Override
    public void injectView(WeakReference<CheatContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(CheatContract.Model model) {
        this.model = model;
    }

}
