using Pinecone;

namespace NLPLinIntro
{
    class Program
    {
        static async Task Main(string[] args)
        {
            using var pinecone = new PineconeClient("42303d79-a94b-4dd0-8dc5-dccd518bbe97", "gcp-starter");

            // Check if the index exists and create it if it doesn't
            // Depending on the storage type and infrastructure state this may take a while
            // Free tier is limited to 1 index only
            var indexName = "umesh-index";
            var indexList = await pinecone.ListIndexes();

            if (!indexList.Contains(indexName))
            {
                await pinecone.CreateIndex(indexName, 1536, Metric.Cosine);
            }

            // Get the Pinecone index by name (uses gRPC by default).
            // The index client is thread-safe, consider caching and/or
            // injecting it as a singleton into your DI container.
            using var index = await pinecone.GetIndex(indexName);

            // Create and upsert vectors
            var vector_Arr = new float[1536];
            vector_Arr[0] = 0.1f;
            vector_Arr[1] = 0.2f;
            vector_Arr[2] = 0.3f;

            var first = new Vector
            {
                Id = "first",
                // Zeroed-out placeholder vector, this is where you put the embeddings unless using sparse vectors
                Values = new float[1536],
                Metadata = new()
                {
                    ["new"] = true,
                    ["price"] = 75,
                    ["tags"] = new string[] { "tag1", "tag2" }
                }
            };

            var second = new Vector
            {
                Id = "second",
                Values = new float[1536],
                Metadata = new() { ["price"] = 100 }
            };

            await index.Upsert([first, second]);

            // Specify metadata filter to query the index with
            var priceRange = new MetadataMap
            {
                ["price"] = new MetadataMap
                {
                    ["$gte"] = 10,
                    ["$lte"] = 80
                }
            };

            // Partially update a vector (allows to update dense/sparse/metadata properties only)
            //await index.Update("second", metadata: new() { ["price"] = 99 });

            // Query the index by embedding and metadata filter
            var results = await index.Query(
                new float[1536],
                topK: 1,
                filter: priceRange,
                includeMetadata: true);

            Console.WriteLine("---------------------------" + results.Count());
            Console.WriteLine(string.Join('\n', results.SelectMany(v => v.Metadata!)));


            // Remove the example vectors we just added
            await index.Delete(["first", "second" ]);
        }
    }
}