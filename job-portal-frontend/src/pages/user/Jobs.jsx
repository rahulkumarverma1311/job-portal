import { Sparkles, Wand2 } from "lucide-react";
import { Button } from "@/components/ui/Button";

const Jobs = () => {
  return (
    <div>
      <section className="bg-linear-to-br from-primary via-blue-950 to-indigo-950 py-12 px-4 flex flex-col items-center justify-center">
        <div className=" min-w-4xl  max-w-4xl max-auto text-center">
          <div className="inline-flex items-center gap-2 text-sm font-medium text-white rounded-full mb-4 bg-white/15 py-1.5 px-4 backdrop-blur-sm">
            <Sparkles className="h-4 w-4"></Sparkles>
            AI-Powered Job Search
          </div>
          <h1 className="text-3xl sm:text-4xl font-bold text-white mb-2">
            Find Your Next Opportunity
          </h1>
          <p className="text-blue-100 mb-8 text-sm sm:text-base">
            Discover the perfect job match with our Ai-powered search
            engine{" "}
          </p>

          {/* Ai search card */}
          <div className="bg-white rounded-2xl shadow-xl p-4 space-y-3">
            <div className="relative">
              <Wand2 className="absolute left-3  top-3 h-4 w-4 text-slate-400 pointer-events-none" />
              <textarea
                placeholder={
                  "Describe the job you looking....\n E.g. Java Developer with 5 year of experience"
                }
                className="w-full resize-none rounded-lg border border-slate-200 bg-slate-50 pl-9 pr-4 py-2.5 text-sm text-slate-800 placeholder:text-slate-400 focus:outline-none  transition"
              />
            </div>
            <div className="flex items-center justify-between">
              <p className="test-xs text-slate-400">
                Tip:Ctrl + Enter to search
              </p>
              <Button className="rounded-xl px-6 py-6 cursor-pointer">
                <Wand2 className="h-4 w-4 mr-1.5" /> Search With AI
              </Button>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Jobs;
