<h1><img src="Styling/xml.png" width= 40 height=40/><img src="Styling/XMLParser.svg" alt="XMLParser"/></h1>
<ul>
    <li>
        <h2><img src="Styling/overview icon better.png" width= 30 height=30/> Project overview:</h2>
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
            <li>File reading</li>
            <li>Error correction</li>
            <li>Formatting</li>
            <li>Parsing</li>
            <li>Tree</li>
            <li>JSON Conversion</li>
            <li>Minifying</li>
            <li>Compression</li>
            <li>Graph</li>
            <li>Tree to Graph</li>
            <li>Graph Visualization</li>
            <li>SNA (Social Network Analysis)</li>
            <li>Undo/Redo</li>
            <li>GUI</li>
        </ul>
    </li>
    <li>
        <h2><img src="Styling/time analysis.png" width=30 height=30/> Time complexity analysis:</h2>
        <h3><i>Time complexity analysis of each main method:</i></h3>
        <li><a href="https://github.com/rye141200/CSE323-DS-Project/blob/7f6f23d41bf14884685c22821a7e10ba484ac376/File%20read%20sample/FileReaderEnhanced.java#L13">readFileParsed()</a>: O(n*m) where n is the size of file, m is the size of tag string</li>
        <li><a href="https://github.com/rye141200/CSE323-DS-Project/blob/7f6f23d41bf14884685c22821a7e10ba484ac376/Error%20Handling/ErrorHandler.java#L57">correctError()</a>: O(m*k+ p*q) where m is the number of lines, k is the average length of a line, p is the           number of error messages, and q is the average length of an error message.</li>
        <li><a href="https://github.com/rye141200/CSE323-DS-Project/blob/7f6f23d41bf14884685c22821a7e10ba484ac376/Error%20Handling/ErrorHandler.java#L28">containsError()</a>: O(n * m), where n is the number of lines and m is the average length of a line.</li>
        <li><a href="https://github.com/rye141200/CSE323-DS-Project/blob/7f6f23d41bf14884685c22821a7e10ba484ac376/Error%20Handling/ErrorHandler.java#L34">detectError()</a>: O(m*k+ p*q) where m is the number of lines, k is the average length of a line, p is the           number of error messages, and q is the average length of an error message.</li>
        <li><a href="https://github.com/rye141200/CSE323-DS-Project/blob/7f6f23d41bf14884685c22821a7e10ba484ac376/Formatting(Prettier)/Formatting.java#L26">formatXML()</a>: O(n*m*k), where n is size of XML file, m is the cost of inserting into the file, and k is the
        identation which would normally reduce to O(n)</li>
    </li>
     
</ul>
