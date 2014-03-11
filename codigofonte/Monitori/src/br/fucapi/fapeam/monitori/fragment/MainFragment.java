package br.fucapi.fapeam.monitori.fragment;


import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.AppMainActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View mainView = inflater
				.inflate(R.layout.main_fragment, container, false);
		Button oButton1 = (Button) mainView.findViewById(R.id.mainmenu_button1);
		oButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((AppMainActivity)MainFragment.this.getActivity()).selectItem(1);
			}
		});
		
		Button oButton2 = (Button) mainView.findViewById(R.id.mainmenu_button2);
		oButton2.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((AppMainActivity)MainFragment.this.getActivity()).selectItem(2);
			}
		});

		return mainView;
	}
}
