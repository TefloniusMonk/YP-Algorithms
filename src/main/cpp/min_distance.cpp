#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <sstream>
#include <stack>

using namespace std;

string find_min_distance();


int main() {
    cout << find_min_distance() << endl;
    return 0;
}

std::string find_min_distance() {
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
    int start_node, to_find;
    cin >> start_node >> to_find;

    bool visited[vertexes + 1];
    for (int i = 0; i < vertexes + 1; ++i) {
        visited[i] = false;
    }

    deque<pair<int, int> > deque;
    deque.push_back(pair < int, int > (start_node, 0));
    while (!deque.empty()) {
        auto p = deque.front();
        deque.pop_front();
        if (p.first == to_find) {
            return to_string(p.second);
        }
        if (visited[p.first]) {
            continue;
        }
        visited[p.first] = true;
        if (graph.find(p.first) != graph.end()) {
            for (int vertex: graph.at(p.first)) {
                deque.push_back(pair < int, int > (vertex, p.second + 1));
            }
        }

    }
    return to_string(-1);
}

