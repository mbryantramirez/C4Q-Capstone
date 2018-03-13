package nyc.c4q.capstone;

import java.util.List;

/**
 * Created by c4q on 3/12/18.
 */

public class CharitySearchResult {

  private List<CharityData> data;
  private String msg;
  private String code;

  public List<CharityData> getData() {
    return data;
  }

  public String getMsg() {
    return msg;
  }

  public String getCode() {
    return code;
  }
}
