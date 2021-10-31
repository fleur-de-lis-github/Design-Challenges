/*Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.

There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

Implement the Solution class:

Solution() Initializes the object of the system.
String encode(String longUrl) Returns a tiny URL for the given longUrl.
String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.
 

Example 1:

Input: url = "https://leetcode.com/problems/design-tinyurl"
Output: "https://leetcode.com/problems/design-tinyurl"

Explanation:
Solution obj = new Solution();
string tiny = obj.encode(url); // returns the encoded tiny url.
string ans = obj.decode(tiny); // returns the original url after deconding it.
 

Constraints:

1 <= url.length <= 104
url is guranteed to be a valid URL. */

public class Codec {
    Map<String ,String> mUrl;
    public Codec(){
        mUrl=new HashMap();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String newUrl=Long.toString(longUrl.hashCode(),16);
        mUrl.put(newUrl,longUrl);
        return newUrl;
        
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return mUrl.get(shortUrl);
    }
}

/* Idea:
Since the characters we can use in the short url are limited just as much as the characters that can be used to make up the long url, there's not much legitimate compression that can realistically be done.

The only generally feasible option is to use a map to act as a lookup table for codes and either use a hashing function or a random code generator to generate the code. Since we're storing the information anyway (hashes only work one-way), we might as well just use a random code generator (getCode()).

Based on the example, we can create a function that creates a random 6-character code, using the 62 alphanumeric characters. We should make sure to come up with a new code in the rare case that we randomly create a duplicate.

To avoid having to encode the same url twice with different random codes, we can create a reverse lookup table (urlDB) to store already encoded urls.

The decode function will just return the entry from the code map (codeDB). */

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
