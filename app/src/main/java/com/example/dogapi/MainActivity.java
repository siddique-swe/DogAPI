package com.example.dogapi;
import com.bumptech.glide.Glide;
import com.example.dogapi.model.DogResponse;
import com.example.dogapi.network.DogApiService;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.dogapi.RetrofitInstance;
public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        // Set initial message
        textView.setText("Fetching a dog image...");

        // Fetch initial image
       // fetchRandomDogImage();

        // Set button click listener
        button.setOnClickListener(v -> fetchRandomDogImage());
    }

    private void fetchRandomDogImage() {
        // Make API call to fetch random dog image
        RetrofitInstance.getApiService().getRandomDogImage().enqueue(new Callback<DogResponse>() {
            @Override
            public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String dogImageUrl = response.body().getMessage();
                    // Load image into ImageView using Glide
                    Glide.with(MainActivity.this)
                            .load(dogImageUrl)
                            .into(imageView);

                    // Update TextView
                    textView.setText("Here's a random dog!");
                } else {
                    textView.setText("Failed to load image!");
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DogResponse> call, Throwable t) {
                textView.setText("Error occurred!");
                Toast.makeText(MainActivity.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
