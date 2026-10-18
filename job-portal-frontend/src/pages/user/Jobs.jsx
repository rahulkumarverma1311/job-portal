import { Sparkles, Wand2 } from "lucide-react";
import { Button } from "@/components/ui/Button";

const Jobs = () => {
  return (
    <div>
      <section className="bg-linear-to-br from-primary via-blue-950 py-12 px-4">
        <div className="max-w-4xl max-auto text-center">
          <div className="inline-flex items-center gap-2 text-sm font-medium text-white rounded-full mb-4 bg-white/15 py-1.5 px-4 backdrop-blur-sm">
            <Sparkles className="h-4 w-4"></Sparkles>
            AI-Powered Job Search
          </div>
          <h1>Find Your Next Opportunity</h1>
          <p>
            Discover the perfect job match with our Ai-powered search
            engine{" "}
          </p>

          {/* Ai search card */}
          <div className="bg-white rounded-2xl shadow-xl p-4 space-y-3">
            <div>
              <Wand2 />
              <textarea />
            </div>
            <div>
              <p>Tip:Ctrl + Enter to search</p>
              <Button>
                <Wand2 /> Search With AI
              </Button>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Jobs;
