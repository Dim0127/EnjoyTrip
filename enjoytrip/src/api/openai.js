import { openaiAxios } from "@/utils/http-commons";

const openai = openaiAxios();

const highlight =
  "나의 질문에 적절한 답변 10개를 한글 단어로, ','로 구분해서, 구체적인 여행 장소 이름으로만 대답해줘. \n";
function getResponse(when, where, who) {
  const query =
    "한글로만 대답해줘. \n 단어로만 대답해줘. \n 해당 질문에 적합한 단어 10개만 반환해줘. \n 해당 물음에 대한 답변 단어 10게를 ,로만 구분해줘. \n 앞뒤 사족 붙이지 말아줘. \n" +
    "나의 질문 : 대한민국에서 여행을 갈 예정인데, 구체적인 여행 장소 이름을 추천받고 싶어. " +
    "[ 언제 ? " +
    when +
    ", 어디를 ? " +
    where +
    ", 누구랑 ? " +
    who +
    "] 질문 끝. \n" +
    highlight +
    highlight +
    highlight +
    highlight +
    highlight;
  return new Promise((resolve) => {
    openai
      .post("/chat/completions", {
        model: "gpt-3.5-turbo",
        messages: [{ role: "user", content: query }],
        max_tokens: 60,
      })
      .then((response) => {
        const answer = response.data.choices[0].message.content.trim();
        console.log("Answer: ", answer);
        const spots = answer.split(",").map((spot) => spot.trim());
        resolve(spots);
      })
      .catch((error) => {
        console.error("Error:", error.response ? error.response.data : error.message);
        resolve(false);
      });
  });
}
export { getResponse };
