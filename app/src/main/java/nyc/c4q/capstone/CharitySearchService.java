package nyc.c4q.capstone;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by c4q on 3/12/18.
 */

public interface CharitySearchService {

  String CharitySearchAPIKey = "ace138f52e35c6c678a9365f0a4cc195";

  @GET("v1/charitysearch?user_key=ace138f52e35c6c678a9365f0a4cc195&city=New%20York&state=NY")
  Call<CharitySearchResult> getCharitySearchResults();
}
