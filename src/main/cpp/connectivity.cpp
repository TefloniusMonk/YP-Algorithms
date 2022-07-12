#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <sstream>
#include <stack>

std::string find_connectivity();

using namespace std;

int main() {
    cout << find_connectivity();
    return 0;
}

std::string find_connectivity() {
    int vertexes, edges;
    cin >> vertexes >> edges;
    unordered_map<int, vector<int> > graph;
    for (int i = 0; i < edges; i++) {
        int from, to;
        cin >> from >> to;
        vector<int> adjacent;
        if (graph.find(from) != graph.end()) {
            graph.at(from).push_back(to);
        } else {
            adjacent.push_back(to);
            graph.insert(pair<int, vector<int> >(from, adjacent));
        }

        vector<int> inversed_adjacent;
        if (graph.find(to) != graph.end()) {
            graph.at(to).push_back(from);
        } else {
            inversed_adjacent.push_back(from);
            graph.insert(pair<int, vector<int> >(to, inversed_adjacent));
        }
    }
    string builder;
    int connectivityCount = 0;
    bool visited[vertexes + 1];
    for (int i = 0; i < vertexes + 1; ++i) {
        visited[i] = false;
    }
    for (int i = 1; i <= vertexes; i++) {
        if (visited[i]) {
            continue;
        }
        std::stack<int> stack;
        stack.push(i);

        int max_idx = -1;
        bool conn_bit_map[vertexes];
        for (int j = 0; j < vertexes; j++) {
            conn_bit_map[j] = false;
        }
        while (!stack.empty()) {
            int cur_node = stack.top();
            stack.pop();
            if (visited[cur_node]) {
                continue;
            } else {
                visited[cur_node] = true;
            }
            max_idx = max(max_idx, cur_node);
            conn_bit_map[cur_node - 1] = true;

            vector<int> adjacent;
            if (graph.find(cur_node) != graph.end()) {
                for (int vertex: graph.at(cur_node)) {
                    stack.push(vertex);
                }
            }
        }
        for (int j = 1; j <= max_idx; j++) {
            if (conn_bit_map[j - 1]) {
                builder += to_string(j) + " ";
            }
        }
        builder += "\n";
        connectivityCount++;
    }
    return to_string(connectivityCount) + "\n" + builder;
}

