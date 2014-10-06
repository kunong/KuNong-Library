package kunong.android.library.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kunong.android.library.R;
import kunong.android.library.fragment.ImageSliderFragment.ImageSliderController;
import kunong.android.library.fragment.ImageSliderFragment.ImageSliderable;

public class ImageSliderDialogFragment extends FullscreenDialogFragment implements ImageSliderable {

	private static final String ARG_POSITION = "arg_position";

	private ImageSliderable mImageSliderable;
	private ImageSliderFragment mImageSliderFragment;
	private int mPosition;

	public static ImageSliderDialogFragment newInstance(int position) {
		ImageSliderDialogFragment fm = new ImageSliderDialogFragment();

		Bundle args = new Bundle();
		args.putInt(ARG_POSITION, position);

		fm.setArguments(args);

		return fm;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			mPosition = getArguments().getInt(ARG_POSITION);
		} else {
			mPosition = savedInstanceState.getInt(ARG_POSITION, 0);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mImageSliderable = (ImageSliderable) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.image_slider_dialog_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		if (savedInstanceState == null) {
			mImageSliderFragment = ImageSliderFragment.newInstance();
			FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

			transaction.replace(R.id.container, mImageSliderFragment);
			transaction.commit();
		} else {
			mImageSliderFragment = (ImageSliderFragment) getChildFragmentManager().findFragmentById(R.id.container);
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);

		dialog.setOnKeyListener((dialog1, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                mImageSliderFragment.dismiss();

                return true;
            }

            return false;
        });

		return dialog;
	}

	@Override
	public void onImageSliderInitialized(ImageSliderFragment imageSliderFragment) {
		mImageSliderFragment.show(mPosition);
	}

	@Override
	public void onImageSliderDismiss(ImageSliderFragment imageSliderFragment) {
		dismiss();
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstantState) {
		super.onSaveInstanceState(savedInstantState);

		if (mImageSliderFragment != null) {
			savedInstantState.putInt(ARG_POSITION, mImageSliderFragment.getCurrentPosition());
		}
	}

	@Override
	public ImageSliderController getImageSliderController() {
		return mImageSliderable.getImageSliderController();
	}
}
