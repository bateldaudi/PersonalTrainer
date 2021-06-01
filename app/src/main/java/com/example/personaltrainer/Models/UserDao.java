package com.example.personaltrainer.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("select * from User")
    LiveData<List<User>> getAllUsers();

    @Query("select * from User WHERE type = (:userType)")
    LiveData<List<User>> getAllByType(int userType);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... users);

    @Delete
    void delete(User user);
}

/*
public class StudentsListFragment extends Fragment {
    private StudentsListViewModel homeViewModel;
    ListView list;
    MyAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel.getData().observe(getViewLifecycleOwner(),
                (data)->{
                    adapter.notifyDataSetChanged();
                });

        View root = inflater.inflate(R.layout.fragment_students_list, container, false);

        list = root.findViewById(R.id.studentslist_listv);
        adapter = new MyAdapter();
        list.setAdapter(adapter);

        FloatingActionButton fab = root.findViewById(R.id.studentslist_add_btn);
        fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_global_newStudentFragment));

        ProgressBar pb = root.findViewById(R.id.studentslist_progressBar);
        pb.setVisibility(View.GONE);

        Model.instance.studentsLoadingState.observe(getViewLifecycleOwner(),(state)->{
            switch(state){
                case loaded:
                    pb.setVisibility(View.GONE);
                    break;
                case loading:
                    pb.setVisibility(View.VISIBLE);
                    break;
                case error:
                    //TODO: display error message
            }
        });
        return root;
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if (homeViewModel.getData().getValue() != null) {
                return homeViewModel.getData().getValue().size();
            }else{
                return 0;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.student_list_row,null);
            }
            ImageView imageV = convertView.findViewById(R.id.listrow_imagev);
            TextView idTv = convertView.findViewById(R.id.listrow_id);
            TextView nameTv = convertView.findViewById(R.id.listrow_name);

            Student student = homeViewModel.getData().getValue().get(position);
            idTv.setText(student.getId());
            nameTv.setText(student.getName());

            return convertView;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

}

 */