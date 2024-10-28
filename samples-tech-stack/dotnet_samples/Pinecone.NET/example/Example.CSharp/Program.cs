using Pinecone;

using var pinecone = new PineconeClient("42303d79-a94b-4dd0-8dc5-dccd518bbe97", "gcp-starter");

// Check if the index exists and create it if it doesn't
// Depending on the storage type and infrastructure state this may take a while
// Free tier is limited to 1 index only
var indexName = "ramesh1-index";
var indexList = await pinecone.ListIndexes();

if (!indexList.Contains(indexName))
{
    await pinecone.CreateIndex(indexName, 1536, Metric.Cosine);
}

// Get the Pinecone index by name (uses gRPC by default).
// The index client is thread-safe, consider caching and/or
// injecting it as a singleton into your DI container.
using var index = await pinecone.GetIndex(indexName);

pinecone.ConfigureIndex(indexName, replicas: 2, podType: "p2");

// Assuming you have an instance of `index`
// Create and upsert vectors
var vector_Arr = new float[1536];
vector_Arr[0] = 0.1f;
vector_Arr[1] = 0.2f;
vector_Arr[3] = 0.3f;

var vectors = new[]
{
    new Vector
    {
        Id = "vector1",
        Values = vector_Arr,
        Metadata = new MetadataMap
        {
            ["genre"] = "horror",
            ["duration"] = 120
        }
    }
};
await index.Upsert(vectors);

// Fetch vectors by IDs
var fetched = await index.Fetch(["vector1"]);

// Query scored vectors by ID
var results = await index.Query("vector1", topK: 10);

Console.WriteLine("---------------------------" + results.Count());
Console.WriteLine(string.Join('\n', results.SelectMany(v => v.Metadata!)));


// Query scored vectors by a new, previously unseen vector
//var vector = new[] { 0.1f, 0.2f, 0.3f};


//scored = await index.Query(vector, topK: 10);

// Query scored vectors by ID with metadata filter
