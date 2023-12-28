<h1><img src="Styling/xml.png" width= 40 height=40/><img src="Styling/XMLParser.svg" alt="XMLParser"/></h1>
<ul>
    <li>
        <h2><img src="Styling/overview icon better.png" width= 40 height=40/> Project overview:</h2>
        <h3><i>This program aims to:</i></h3>
        <ul>
            <li>Parse XML text</li>
            <li>Converts it to JSON</li>
            <li>Corrects and shows errors in the input file visually and in real-time</li>
            <li>Minifies and compresses the input file</li>
            <li>Visualizes the graph representation of the input XML file</li>
            <li>Performs social network analysis (SNA) on the given data to give useful information about this network</li>
            <li>Supports undo/redo operations on the text</li>
            <li>Saves JSON files and compatible with any XML file (supports XML with attributes)</li>
        </ul>
    </li>
    <li>
        <h2><img src="Styling/Building blocks.png" width= 30 height= 30/> Project building blocks:</h2>
        <h3><i>This project was divided into the following parts as basic building blocks:</i></h3>
        <ul>
            <li><strong>File reading:</strong> <i>reads the input file, parses it and creates a list of all the elements inside the file</i></li>
            <li><strong>Error correction:</strong> <i>corrects missing tags (opening or closing) and mismatching tags</i></li>
            <li><strong>Formatting:</strong> <i>prettifies the file with identation of 2 spaces </i></li>
            <li><strong>Parsing:</strong> <i>takes the input file from file reading block, parses them into useful data to create a tree analogous to HTML DOM tree</i></li>
            <li><strong>Tree:</strong> <i>class used to create the tree</i></li>
            <li><strong>JSON Conversion:</strong> <i>uses the tree created to convert it to JSON</i></li>
            <li><strong>Minifying:</strong> <i>takes input from file reading, minifies the file by eliminating all \n</i></li>
            <li><strong>Compression:</strong> <i>compresses the input file from file reading block, via encoding techniques</i></li>
            <li><strong>Graph:</strong> <i>class used as a blueprint for the graph of social networks </i></li>
            <li><strong>Tree to Graph:</strong> <i>takes the XMLTree as input from Parsing block, converts it to a suitable tree</i></li>
            <li><strong>Graph Visualization (3rd party library)</strong>: <i>social graph created would be adapted to be compatible with this library which shows the visual graph</i> <a href="https://github.com/brunomnsilva/JavaFXSmartGraph">smartgraph library</a></li>
            <li><strong>SNA (Social Network Analysis)</strong>: <i>uses the social graph as input from Tree to Graph block, to make social network analysis</i></li>
            <li><strong>Undo/Redo:</strong> <i>takes input as the text, implemented using stack, undoing and redoing every operation</i></li>
            <li><strong>GUI:</strong> <i>created using JavaFX</i></li>
        </ul>
    </li>
    <li>
        <h2><img src="Styling/time analysis.png" width=30 height=30/> Time complexity analysis:</h2>
        <h3><i>Time complexity analysis of each main method:</i></h3>
        <table>
          <thead>
            <tr>
              <th>Method</th>
              <th>Complexity</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/7f6f23d41bf14884685c22821a7e10ba484ac376/File%20read%20sample/FileReaderEnhanced.java#L13">readFileParsed()</a></td>
              <td>O(n*m) where n is the size of file, m is the size of tag string.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/7f6f23d41bf14884685c22821a7e10ba484ac376/Error%20Handling/ErrorHandler.java#L57">correctError()</a></td>
              <td> O(m*k+ p*q) where m is the number of lines, k is the average length of a line, p is the number of error messages, and q is the average length of an error message.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/7f6f23d41bf14884685c22821a7e10ba484ac376/Error%20Handling/ErrorHandler.java#L28">containsError()</a></td>
              <td>O(n * m), where n is the number of lines and m is the average length of a line.<td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/7f6f23d41bf14884685c22821a7e10ba484ac376/Error%20Handling/ErrorHandler.java#L34">detectError()</a></td>
              <td>O(m*k+ p*q) where m is the number of lines, k is the average length of a line, p is the number of error messages, and q is the average length of an error message.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/7f6f23d41bf14884685c22821a7e10ba484ac376/Formatting(Prettier)/Formatting.java#L26">formatXML()</a></td>
              <td> O(n*m*k), where n is size of XML file, m is the cost of inserting into the file, and k is the identation which would normally reduce to O(n).</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/ecffcc258dfab6b55a95a92833a496b98c818957/Parsing%20and%20JSON/Parsing.java#L15">parseXML()</a></td>
              <td>O(n*m), where n is the size of the file in terms of tags, m is the size of the tag string.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/ecffcc258dfab6b55a95a92833a496b98c818957/Parsing%20and%20JSON/JSONConverter.java#L22">XMLToJSON()</a></td>
              <td>O(n*m^2), where n is the size of file in terms of tags, m is the number of children of nodes.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/ecffcc258dfab6b55a95a92833a496b98c818957/File%20Compression/FileCompression.java#L28">minify()</a></td>
              <td>O(n^2), where n is the size of file in terms of tags.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/ecffcc258dfab6b55a95a92833a496b98c818957/File%20Compression/FileCompression.java#L116">compressFile()</a></td>
              <td>O(n) n is the total number of characters in the whole file.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/ecffcc258dfab6b55a95a92833a496b98c818957/File%20Compression/FileCompression.java#L194">decompressFile()</a></td>
              <td>O(n) n is the total number of characters in the whole file.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/5b60f23b15ea2be4f949eafcca17f456befbab64/Visualization/TreeToGraph.java#L22">convertTreeToGraph()</a></td>
              <td>O(n^3), where n is the number of users in graph</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/dd1954fbb63cc3ba6e6d6ec1596754bc29a6e43d/SNA_Undo/SNA.java#L26">getMostInfluencer()</a></td>
              <td>O(nlogn), where n is the number of users</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/dd1954fbb63cc3ba6e6d6ec1596754bc29a6e43d/SNA_Undo/SNA.java#L39">getMostActive()</a></td>
              <td>O(n*m), where n is number of users, and m is the average number of followers per user</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/dd1954fbb63cc3ba6e6d6ec1596754bc29a6e43d/SNA_Undo/SNA.java#L64">getMutualFollower()</a></td>
              <td>O(m), where m is the total number of followers between the two users.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/dd1954fbb63cc3ba6e6d6ec1596754bc29a6e43d/SNA_Undo/SNA.java#L84">suggestFollowers()</a></td>
              <td>O(n^2 * m), where n is the number of users and m is the average number of followers per user.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/dd1954fbb63cc3ba6e6d6ec1596754bc29a6e43d/SNA_Undo/SNA.java#L105">searchByParagraph()</a></td>
              <td>O(n * m), where n is the number of users and m is the average number of posts per user.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/dd1954fbb63cc3ba6e6d6ec1596754bc29a6e43d/SNA_Undo/SNA.java#L84">searchByTopic()</a></td>
              <td>O(n * m * k), where n is the number of users, m is the average number of posts per user, and k is the average number of topics per post.</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/dd1954fbb63cc3ba6e6d6ec1596754bc29a6e43d/SNA_Undo/UndoRedo.java#L46">undo()</a></td>
              <td>O(1)</td>
            </tr>
            <tr>
              <td><a href="https://github.com/rye141200/CSE323-DS-Project/blob/dd1954fbb63cc3ba6e6d6ec1596754bc29a6e43d/SNA_Undo/UndoRedo.java#L54">redo()</a></td>
              <td>O(1)</td>
            </tr>
          </tbody>
        </table>
    </li>
    <li>
        <h2><img src="Styling/screenshots icon.png" width=30 height=30/> Screenshots:</h2>
        <h3><i>Samples of file while running:</i></h3>
    </li>
    <li>
        <h2><img src="Styling/team members icon.png" width=30 height=30/> Team members:</h2>
        <h3><i>Members:</i></h3>
        <table>
          <thead>
            <tr>
              <th><strong>Name</strong></th>
              <th><strong>ID</strong></th>
              <th><strong>GitHub username</strong></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Ahmad Youssef Mahfouz</td>
              <td>2002238</td>
              <td>rye141200</td>
            </tr>
            <tr>
              <td>Youssef Wael Hamdy Ibrahim</td>
              <td>2001430</td>
              <td>youssefashmawy</td>
            </tr>
            <tr>
              <td>Mohamed Mostafa Mahmoud</td>
              <td>2001299</td>
              <td>mohamed-most</td>
            </tr>
            <tr>
              <td>Fathy Abdlhady Fathy</td>
              <td>2001152</td>
               <td>FathyAbdlhady</td>
            </tr>
            <tr>
              <td>Yousef Shawky Mohamed</td>
              <td>2001500</td>
              <td>thedarkevil987</td>
            </tr>
            <tr>
              <td>Omar Saleh Mohamed</td>
              <td>2001993</td>
              <td>MrMariodude</td>
            </tr>
            <tr>
              <td>Abddullah Mohammed Hassan</td>
              <td>2001803</td>
              <td>AntiHexCode</td>
            </tr>
          </tbody>
        </table>
    </li>
    <li>
        <h2><img src="Styling/youtube icon.png" width=30 height=30/> YouTube video:</h2>
        <h3><a href="#">Amazing video awaits!</a></h3>
    </li>
</ul>
