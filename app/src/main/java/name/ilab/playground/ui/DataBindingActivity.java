package name.ilab.playground.ui;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import name.ilab.playground.R;

//import name.ilab.test.app.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LayoutInflater inflater = getLayoutInflater();
//        ActivityDataBindingBinding binding = ActivityDataBindingBinding.inflate(inflater);

        user = new User();
        user.firstName.set("AAA");
        user.lastName.set("BBB");
        user.password.set("CCC");
//        user.setFirstName("AAA");
//        user.setLastName("BBB");
//        user.setPassword("CCC");

//        ActivityDataBindingBinding binding =
//                DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
//        binding.setUser(user);

//        setContentView(R.layout.activity_data_binding);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("user = " + user);
                user.firstName.set("AAA");
                user.lastName.set("BBB");
                user.password.set("CCC");
//                user.setFirstName("AAA");
//                user.setLastName("BBB");
//                user.setPassword("CCC");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static class User {

        public final ObservableField<String> firstName = new ObservableField<>();
        public final ObservableField<String> lastName = new ObservableField<>();
        public final ObservableField<String> password = new ObservableField<>();

//        private String firstName;
//        private String lastName;
//        private String password;
//
//        public String getFirstName() {
//            return firstName;
//        }
//
//        public void setFirstName(String firstName) {
//            this.firstName = firstName;
//        }
//
//        public String getLastName() {
//            return lastName;
//        }
//
//        public void setLastName(String lastName) {
//            this.lastName = lastName;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
        public void onSignUp(View view) {
            System.out.println("User.onSignUp : " + toString());
        }

        public void onSignIn(View view) {
            System.out.println("User.onSignIn : " + toString());
        }

//        @Override
//        public String toString() {
//            return "User{" +
//                    "firstName='" + firstName + '\'' +
//                    ", lastName='" + lastName + '\'' +
//                    ", password='" + password + '\'' +
//                    '}';
//        }


        @Override
        public String toString() {
            return "User{" +
                    "firstName=" + firstName.get() +
                    ", lastName=" + lastName.get() +
                    ", password=" + password.get() +
                    '}';
        }
    }

}
