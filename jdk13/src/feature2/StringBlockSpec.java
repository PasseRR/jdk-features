package feature2;

/**
 * @author xiehai
 * @date 2022/10/31 16:42
 */
public class StringBlockSpec {
    // JDK13之前
    String content = "{\n"
        + "    \"upperSummary\": null,\n"
        + "    \"sensitiveTypeList\": null,\n"
        + "    \"gmtModified\": \"2011-08-05 10:50:09\",\n"
        + "    \"lowerGraph\": null,\n"
        + "    \"signature\": \"\",\n"
        + "    \"appName\": \"xxx\",\n"
        + "    \"lowerSummary\": null,\n"
        + "    \"gmtCreate\": \"2011-08-05 10:50:09\",\n"
        + "    \"type\": \"CALL\",\n"
        + "    \"name\": \"xxxx\",\n"
        + "    \"subType\": \"yyy\",\n"
        + "    \"id\": 1,\n"
        + "    \"projectId\": 1,\n"
        + "    \"status\": 1\n"
        + "}";

    String content13 = """
        {
            "upperSummary": null,
            "sensitiveTypeList": null,
            "gmtModified": "2011-08-05 10:50:09",
            "lowerGraph": null,
            "signature": "",
            "appName": "xxx",
            "lowerSummary": null,
            "gmtCreate": "2011-08-05 10:50:09",
            "type": "CALL",
            "name": "xxxx",
            "subType": "yyy",
            "id": 1,
            "projectId": 1,
            "status": 1
        }
        """;
}
