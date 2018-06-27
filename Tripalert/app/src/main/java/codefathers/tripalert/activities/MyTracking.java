package codefathers.tripalert.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import codefathers.tripalert.R;
import codefathers.tripalert.models.TrackingModel;
import codefathers.tripalert.viewModels.HomeScreenViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyTracking.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyTracking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyTracking extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ConstraintLayout notCreatedLayout;
    private LinearLayout createdLayout;


    private HomeScreenViewModel viewModel;

    private OnFragmentInteractionListener mListener;

    public MyTracking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyTracking.
     */
    // TODO: Rename and change types and number of parameters
    public static MyTracking newInstance(String param1, String param2) {
        MyTracking fragment = new MyTracking();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel =  ViewModelProviders.of(getActivity()).get(HomeScreenViewModel.class);
        viewModel.getCreatedTracking().observe(this, new Observer<TrackingModel>() {
            @Override
            public void onChanged(@Nullable TrackingModel trackingModel) {
                if(trackingModel != null ) {
                    TextView txt = (TextView) getView().findViewById(R.id.currStart);
                    TextView txt2 = (TextView) getView().findViewById(R.id.currDestination);
                    TextView txt3 = (TextView) getView().findViewById(R.id.currStartedAt);
                    TextView txt4 = (TextView) getView().findViewById(R.id.currEstimated);
                    txt.setText(trackingModel.getStartingPoint());
                    txt2.setText(trackingModel.getDestinationName());
                    txt4.setText(String.valueOf(trackingModel.getEstimatedTime()));
                    txt3.setText("dunno");
                    createdLayout.setVisibility(View.VISIBLE);
                    notCreatedLayout.setVisibility(View.INVISIBLE);
                }else{
                    createdLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_my_tracking, container, false);
        this.createdLayout = (LinearLayout) v.findViewById(R.id.created) ;
        this.notCreatedLayout = (ConstraintLayout) v.findViewById(R.id.notCreated) ;
        Button settingsBtn = (Button) v.findViewById(R.id.newTrackingBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getActivity(), SelectContacts.class));
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
