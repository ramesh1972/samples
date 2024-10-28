package example;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class OpenAiApiExample {
    public static void main(String... args) {
        String token = "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";
        OpenAiService service = new OpenAiService(token, Duration.ofSeconds(30));

        final List<ChatMessage> messages1 = new ArrayList<>();
        final ChatMessage systemMessage1 = new ChatMessage(ChatMessageRole.SYSTEM.value(), "Capital of Ihdia.");
        messages1.add(systemMessage1);
        ChatCompletionRequest chatCompletionRequest1 = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages1)
                .n(1)
                .temperature(.1)
                .maxTokens(50)
                .logitBias(new HashMap<>())
                .build();
        service.createChatCompletion(chatCompletionRequest1).getChoices().forEach( (c) -> { 
        	System.out.println(c.getMessage().getContent()); });
        
        System.out.println("--------------------------------------------------------");
        System.out.println("\nCreating completion...");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("babbage-002")
                .prompt("Somebody once told me the world is gonna roll me")
                .echo(true)
                .user("testing")
                .temperature(.8)
                .n(3)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach( (c) -> { 
        	System.out.println(c.getText());});

        System.out.println("\nCreating Image...");
        CreateImageRequest request = CreateImageRequest.builder()
                .prompt("A cow breakdancing with a turtle")
                .build();

        System.out.println("--------------------------------------------------------");
        System.out.println("\nImage is located at:");
        System.out.println(service.createImage(request).getData().get(0).getUrl());

        System.out.println("--------------------------------------------------------");
        System.out.println("Streaming chat completion...");
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "You are a dog and will speak as such.");
        messages.add(systemMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(50)
                .logitBias(new HashMap<>())
                .build();

        ArrayList<ChatCompletionChoice> choices3 = new ArrayList<ChatCompletionChoice>();
        service.streamChatCompletion(chatCompletionRequest)
                .doOnError(Throwable::printStackTrace)
                .blockingForEach((c2) -> { c2.getChoices().forEach( (c1) -> { 
                	System.out.println(c1.getMessage().getContent());
                	choices3.add(c1);
                	}); 
                });

        choices3.forEach( (c) -> { 
        	if (c.getMessage().getContent() != null)
                System.out.print( c.getMessage().getContent());
        });
        
        service.shutdownExecutor();
    }
}
