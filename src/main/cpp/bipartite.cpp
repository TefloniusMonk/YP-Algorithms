#include <iostream>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>
#include <sstream>
#include <stack>
#include <string>

using namespace std;

const string YES = "YES";
const string NO = "NO";
unordered_set<int> red_set;
unordered_set<int> blue_set;

enum Color {
    RED, BLUE
};

class Node {
public:
    int val;
    Color color;

    Node(int val, Color color) : val(val), color(color) {
        if (color == BLUE) {
            blue_set.insert(val);
        } else {
            red_set.insert(val);
        }
    }
};

std::string wrap();

bool is_painted(int i);

bool same_color(Node cur_node, int next_node);

Color next_color(Color color);

int main() {
    cout << wrap();
    return 0;
}

std::string wrap() {
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


    for (int i = 1; i <= vertexes; i++) {
        if (is_painted(i)) {
            continue;
        }
        std::deque<Node> deq;
        deq.push_front(Node(i, BLUE));

        while (!deq.empty()) {
            Node cur_node = deq.front();
            deq.pop_front();

            if (graph.find(cur_node.val) == graph.end()) {
                continue;
            }
            for (int nextNode: graph.at(cur_node.val)) {
                if (is_painted(nextNode)) {
                    if (same_color(cur_node, nextNode)) {
                        return NO;
                    }
                    continue;
                }
                deq.push_back(Node(nextNode, next_color(cur_node.color)));
            }
        }

    }
    return YES;
}

Color next_color(Color color) {
    return color == BLUE ? RED : BLUE;
}

bool same_color(Node cur_node, int next_node) {
    if (cur_node.color == BLUE) {
        return blue_set.find(next_node) != blue_set.end();
    }
    return red_set.find(next_node) != red_set.end();
}

bool is_painted(int i) {
    return blue_set.find(i) != blue_set.end() || red_set.find(i) != red_set.end();
}

