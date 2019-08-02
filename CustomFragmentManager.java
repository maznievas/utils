package com.stellium.bigdig.stellium1.managers;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.stellium.bigdig.stellium1.R;
import com.stellium.bigdig.stellium1.StelliumApplication;
import com.stellium.bigdig.stellium1.data.dataRepositories.stationRepository.StationRepository;
import com.stellium.bigdig.stellium1.domain.usecase.ArtistUseCase;
import com.stellium.bigdig.stellium1.managers.listeners.CustomFragmentManagerListener;
import com.stellium.bigdig.stellium1.managers.util.Const;
import com.stellium.bigdig.stellium1.view.dialogsFragment.MenuDialogFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllAlbumsFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllArchivesFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllCategoriesFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllChartsFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllListenersSetFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllPlaylistsFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllStreamFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllStreamsCategoriesFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllTopPlaylistsFragmentMainPage;
import com.stellium.bigdig.stellium1.view.fragments.AllTopPlaylistsFromStationFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllTracksFragment;
import com.stellium.bigdig.stellium1.view.fragments.AllVideosFragment;
import com.stellium.bigdig.stellium1.view.fragments.AuthorizeFragment;
import com.stellium.bigdig.stellium1.view.fragments.FollowYouFragment;
import com.stellium.bigdig.stellium1.view.fragments.LoginFragment;
import com.stellium.bigdig.stellium1.view.fragments.NoNetworkFragment;
import com.stellium.bigdig.stellium1.view.fragments.PasswordRecoverFragment;
import com.stellium.bigdig.stellium1.view.fragments.SearchFragment;
import com.stellium.bigdig.stellium1.view.fragments.ShareFragment;
import com.stellium.bigdig.stellium1.view.fragments.SingleArchiveFragment;
import com.stellium.bigdig.stellium1.view.fragments.SinglePlaylistFragment;
import com.stellium.bigdig.stellium1.view.fragments.SplashFragment;
import com.stellium.bigdig.stellium1.view.fragments.TrackAddedSuccessfullyFragment;
import com.stellium.bigdig.stellium1.view.fragments.TrackAlreadyAddedFragment;
import com.stellium.bigdig.stellium1.view.fragments.WelcomeFragment;
import com.stellium.bigdig.stellium1.view.fragments.archives.FragmentArchivePlaylists;
import com.stellium.bigdig.stellium1.view.fragments.archives.FragmentArchivesArtists;
import com.stellium.bigdig.stellium1.view.fragments.charts.ChartsFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.CreateStationFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.EditProfileFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.FeedbackFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.LibraryFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.MyStationsFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.NicknameFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.PasswordFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.PlaylistsFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.RecentFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.SavedTracksFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.SavedVideosFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.SettingsFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.SubscriptionsFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.registration.RegisterFragment;
import com.stellium.bigdig.stellium1.view.fragments.library.registration.RegisterFragmentPrivacyPolicy;
import com.stellium.bigdig.stellium1.view.fragments.library.registration.RegisterFragmentSetBirthdayAndGender;
import com.stellium.bigdig.stellium1.view.fragments.library.registration.RegisterFragmentStepSetCountry;
import com.stellium.bigdig.stellium1.view.fragments.library.registration.RegisterFragmentSuccess;
import com.stellium.bigdig.stellium1.view.fragments.main.MainFragment;
import com.stellium.bigdig.stellium1.view.fragments.main.Test1Fragment;
import com.stellium.bigdig.stellium1.view.fragments.main.artists.AllArtistsFragment;
import com.stellium.bigdig.stellium1.view.fragments.main.artists.GalleryFragment;
import com.stellium.bigdig.stellium1.view.fragments.main.artists.SingleArtistFragment;
import com.stellium.bigdig.stellium1.view.fragments.main.artists.SingleGalleryItemFragment;
import com.stellium.bigdig.stellium1.view.fragments.player.PlayerFragment;
import com.stellium.bigdig.stellium1.view.fragments.survey.FragmentSurveyChooseArtists;
import com.stellium.bigdig.stellium1.view.fragments.survey.FragmentSurveyChooseTrands;
import com.stellium.bigdig.stellium1.view.fragments.survey.FragmentSurveyWelcome;
import com.stellium.bigdig.stellium1.view.fragments.trends.SingleStationFragment;
import com.stellium.bigdig.stellium1.view.fragments.trends.TrendsFragment;
import com.stellium.bigdig.stellium1.view.fragments.userPlaylist.ChoosePlaylistFragment;
import com.stellium.bigdig.stellium1.view.fragments.userPlaylist.PlaylistCreateFragment;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.gujun.android.taggroup.TagGroup;

import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_ALBUMS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_ARCHIVES;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_ARTISTS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_CHARTS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_LISTENERS_SET;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_PLAYLISTS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_STREAMS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_STREAMS_CATEGORIES;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_TOP_PLAYLISTS_MAIN_PAGE;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_TOP_PLAYLISTS_STATION;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_TRACKS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_TRENDS_CATEGORIES;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ALL_VIDEOS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ARCHIVE_ALBUMS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_ARCHIVE_TRACKS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_AUTHORIZE;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_CHARTS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_CHOOSE_PLAYLIST;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_CREATE_PLAYLIST;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_CREATE_STATION;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_EDIT_PROFILE;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_FEEDBACK;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_FOLLOW_YOU;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_GALLERY;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_LIBRARY;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_LOGIN;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_MY_STATIONS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_NICKNAME;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_NO_NETWORK;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_PASSWORD;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_PLAYER;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_PLAYLISTS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_RECENT;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_RECOVER_PASSWORD;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_REGISTER;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_REGISTER_PRIVACY_POLICY;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_REGISTER_STEP_SET_BIRTHDAY_AND_GENDER;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_REGISTER_STEP_SET_COUNTRY;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_REGISTER_SUCCESS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SAVED_TRACKS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SAVED_VIDEOS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SEARCH;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SETTINGS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SHARE;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SINGLE_ARCHIVE;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SINGLE_ARTIST;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SINGLE_GALLERY_ITEM;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SINGLE_PLAYLIST;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SINGLE_STATION;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SPLASH;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SUBSCRIPTIONS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SURVEY_CHOOSE_ARTISTS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SURVEY_CHOOSE_TRANDS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_SURVEY_WELCOME;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_TEST1;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_TRACK_ALREADY_EXIST;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_TRACK_SUCCESSFULLY_ADDED;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_TRANDS;
import static com.stellium.bigdig.stellium1.managers.util.Const.FragmentKey.FRAGMENT_WELCOME;

public class CustomFragmentManager {

    Map<Integer, Stack<Fragment>> customFragmentManagerMap;
    private CustomFragmentManagerListener customFragmentManagerListener;
    private final String TAG = "mLog";

    @Inject
    ArtistUseCase artistUseCase;

    @Inject
    StationRepository stationRepository;

    @Inject
    public CustomFragmentManager() {
        customFragmentManagerMap = new LinkedHashMap<>();
        StelliumApplication.getAppComponent().inject(this);
    }

    public void setCustomFragmentManagerListener(CustomFragmentManagerListener customFragmentManagerListener) {
        this.customFragmentManagerListener = customFragmentManagerListener;
    }

    // ADDING FRAGMENTS

    public Fragment addFragmentWithReplace(int fragmentKeyStack, int fragmentId) {
        Fragment fragment = returnFragmentById(fragmentId);

        if (customFragmentManagerMap.get(fragmentKeyStack) == null) {
            Stack<Fragment> fragmentStack = new Stack<>();
            fragmentStack.push(fragment);
            customFragmentManagerMap.put(fragmentKeyStack, fragmentStack);
        }
        //for adding embedded fragments
        else {
            Stack<Fragment> fragments = customFragmentManagerMap.get(fragmentKeyStack);
            customFragmentManagerMap.remove(fragmentKeyStack);
            boolean contains = false;
            try {
                for (Fragment fragmentFromStack : fragments) {
                    //  Log.d("customFragmentManager", "fragmentFromStack " + fragmentFromStack.toString() + " fragment " + fragment.toString());
                    if (fragmentFromStack != null) {
                        if (fragmentFromStack.getClass().toString().equals(fragment.getClass().toString())) {
                            //contains = true;
                            fragments.remove(fragmentFromStack);
                        }
                    }
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            fragments.add(fragment);

            customFragmentManagerMap.put(fragmentKeyStack, fragments);
        }

        displayStackContent();

        return fragment;
    }

    public Fragment addFragmentWithoutReplace(int fragmentKeyStack, int fragmentId) {
        Fragment fragment = returnFragmentById(fragmentId);
        setSelectedBottomNavigationItem(fragmentKeyStack);
        return addFragmentWithoutReplace(fragmentKeyStack, fragment);
    }

    public Fragment addFragmentWithoutReplace(int fragmentKeyStack, Fragment fragment) {
        if (customFragmentManagerMap.get(fragmentKeyStack) == null) {
            Stack<Fragment> fragmentStack = new Stack<>();
            fragmentStack.push(fragment);
            customFragmentManagerMap.put(fragmentKeyStack, fragmentStack);
        }
        //for adding embedded fragments
        else {
            customFragmentManagerMap.get(fragmentKeyStack).add(fragment);
        }

        displayStackContent();

        return fragment;
    }

    public Fragment addRootFragment(int fragmentKeyStack, int fragmentId) {
        Fragment fragment = returnFragmentById(fragmentId);

        if (customFragmentManagerMap.get(fragmentKeyStack) != null) {
            customFragmentManagerMap.remove(fragmentKeyStack);
        }

        Stack<Fragment> fragmentStack = new Stack<>();
        fragmentStack.push(fragment);
        customFragmentManagerMap.put(fragmentKeyStack, fragmentStack);

        setSelectedBottomNavigationItem(fragmentKeyStack);
        Const.CURRENT_SCREEN_KEY = fragmentKeyStack;

        displayStackContent();

        return fragment;
    }

    public Fragment addRootFragmentWithoutBackstack(int fragmentKeyStack, int fragmentId) {
        Fragment fragment = returnFragmentById(fragmentId);

        if (customFragmentManagerMap.get(fragmentKeyStack) != null) {
            customFragmentManagerMap.remove(fragmentKeyStack);
        }

//        Stack<Fragment> fragmentStack = new Stack<>();
//        fragmentStack.push(fragment);
//        customFragmentManagerMap.put(fragmentKeyStack, fragmentStack);

        setSelectedBottomNavigationItem(fragmentKeyStack);
        Const.CURRENT_SCREEN_KEY = fragmentKeyStack;

        displayStackContent();

        return fragment;
    }

    //REMOVING FRAGMENTS

    public void removeLastFragmentFrom(int fragmentKeyStack, int fragmentKey) {
        Stack<Fragment> currentStack = customFragmentManagerMap.get(fragmentKeyStack);
        Fragment fragment = returnFragmentById(fragmentKey);
        if (currentStack != null && fragment != null) {
            if (currentStack.size() > 0) {
                for (Fragment fragmentFromStack : currentStack) {
                    if (fragmentFromStack != null && fragment != null) {
                        if (fragment.getClass().toString().equals(fragmentFromStack.getClass().toString())) {
                            currentStack.remove(fragmentFromStack);
                        }
                    }
                }
            }
        }
    }

    // GETTING EXISTING FRAGMENTS

    public Fragment getLastFragmentFromStack(int fragmentKeyStack) {
        Stack<Fragment> fragments = customFragmentManagerMap.get(fragmentKeyStack);
        Fragment fragment = fragments.lastElement();
        customFragmentManagerMap.remove(fragmentKeyStack);
        customFragmentManagerMap.put(fragmentKeyStack, fragments);
        return fragment;
    }

    // DISPLAYING FRAGMENTS

    public void displayFragment(Fragment fragment) {
        customFragmentManagerListener.displayFragmentListener(fragment);
    }

    public void displayDialog(DialogFragment dialogFragment) {
        customFragmentManagerListener.displayDialogFragment(dialogFragment);
    }

    public void setSelectedBottomNavigationItem(int stackId) {
        customFragmentManagerListener.setSelectedBottomItemListener(detectParentGroup(stackId));
        Const.CURRENT_SCREEN_KEY = stackId;
    }

    // BACK PRESS

    public void onBackPressed() {
        Stack<Fragment> lastFragmentStack = null;
        try {
            lastFragmentStack = (Stack<Fragment>) customFragmentManagerMap.values().toArray()[customFragmentManagerMap.size() - 1];
            //Stack<Fragment> lastFragmentStack = customFragmentManagerMap.get(customFragmentManagerMap.size()-1);
        } catch (Exception e) {
            e.printStackTrace();
            customFragmentManagerListener.finishActivity();
        }

        int key = -1;
        for (Map.Entry entry : customFragmentManagerMap.entrySet()) {
            if (lastFragmentStack.equals(entry.getValue())) {
                key = (int) entry.getKey();
                break;
            }
        }
        Log.d("screenKey", "key: " + key);
        getLastFragment(lastFragmentStack, key);
    }

    public void getLastFragment(Stack<Fragment> lastFragmentStack, int key) {
        if (lastFragmentStack != null) {
            //todo it was 0 but now here is 1. Should remove if it will not work
            if (lastFragmentStack.size() > 0) {
                Fragment currentFragment = lastFragmentStack.get(lastFragmentStack.size() - 1);
                if (currentFragment instanceof SingleStationFragment) {
                    stationRepository.getStation()
                            .flatMapCompletable(station -> {
                                return stationRepository.delete(station);
                            })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(() -> {
                                Log.d("mLog", "success deleting of station");
                            }, throwable -> {
                                Log.e("mLog", "errorHandled while deleting station");
                                throwable.printStackTrace();
                            });
                }
                if (currentFragment instanceof SingleArtistFragment) {
                    lastFragmentStack.pop();
                    if (lastFragmentStack.size() > 0) {
                        Fragment lastFragment = lastFragmentStack.get(lastFragmentStack.size() - 1);
                        deleteArtistAndDisplayFragment(lastFragment, key);
                    }
                } else {
                    lastFragmentStack.pop();

                    if (lastFragmentStack.size() > 0) {
                        Fragment lastFragment = lastFragmentStack.get(lastFragmentStack.size() - 1);
//                        if (lastFragment instanceof SingleArtistFragment) {
//                            deleteArtistAndDisplayFragment(lastFragment, key);
//                        } else {
                        customFragmentManagerListener.displayFragmentListener(lastFragment);
                        customFragmentManagerListener.setSelectedBottomItemListener(detectParentGroup(key));
                        // }
                        return;
                    } else {
                        customFragmentManagerMap.remove(key);
                        if (customFragmentManagerMap.size() > 0) {
                            lastFragmentStack = (Stack<Fragment>) customFragmentManagerMap.values().toArray()[customFragmentManagerMap.size() - 1];
                            //  key = customFragmentManagerMap.lastEntry().getKey();
                            for (Map.Entry entry : customFragmentManagerMap.entrySet()) {
                                if (lastFragmentStack.equals(entry.getValue())) {
                                    key = (int) entry.getKey();
                                    break;
                                }
                            }
                            if (lastFragmentStack.size() > 0) {
                                Fragment lastFragment = lastFragmentStack.get(lastFragmentStack.size() - 1);
                                customFragmentManagerListener.displayFragmentListener(lastFragment);
                                customFragmentManagerListener.setSelectedBottomItemListener(detectParentGroup(key));
                            }
                            return;
                        } else
                            customFragmentManagerListener.finishActivity();
                    }
                }
            } else {
                customFragmentManagerMap.remove(key);
                if (customFragmentManagerMap.size() > 0) {
                    lastFragmentStack = (Stack<Fragment>) customFragmentManagerMap.values().toArray()[customFragmentManagerMap.size() - 1];
                    //key = customFragmentManagerMap.keySet().//.entrySet(customFragmentManagerMap.size()-1).getKey();
                    for (Map.Entry entry : customFragmentManagerMap.entrySet()) {
                        if (lastFragmentStack.equals(entry.getValue())) {
                            key = (int) entry.getKey();
                            break;
                        }
                    }
                    if (lastFragmentStack.size() > 0) {
                        Fragment lastFragment = lastFragmentStack.get(lastFragmentStack.size() - 1);
                        customFragmentManagerListener.displayFragmentListener(lastFragment);
                        customFragmentManagerListener.setSelectedBottomItemListener(detectParentGroup(key));
                        return;
                    }
                } else
                    customFragmentManagerListener.finishActivity();
            }
        } else customFragmentManagerListener.finishActivity();
    }

    public void getLastFragment() {
        Stack<Fragment> lastFragmentStack = (Stack<Fragment>) customFragmentManagerMap.values().toArray()[customFragmentManagerMap.size() - 1];
        customFragmentManagerListener.displayFragmentListener(lastFragmentStack.lastElement());
        Set<Integer> set = customFragmentManagerMap.keySet();
        Integer key = 0;
        for (Iterator<Integer> it = set.iterator(); it.hasNext(); ) {
            key = it.next();
            Log.d(TAG, "key + " + key);
        }
        setSelectedBottomNavigationItem(key.intValue());
    }

    public Fragment returnLastFrgament(){
        Stack<Fragment> lastFragmentStack = (Stack<Fragment>) customFragmentManagerMap.values().toArray()[customFragmentManagerMap.size() - 1];
        return lastFragmentStack.get(lastFragmentStack.size() -1);
    }

    // HELPERS

    public Fragment returnFragmentById(int fragmentId) {
        Fragment fragment = null;
        switch (fragmentId) {
            case Const.FragmentKey.FRAGMENT_MAIN:
                fragment = new MainFragment();
                ((MainFragment) fragment).setShouldAddToBackStack(true);
                ((MainFragment) fragment).setNeedNavigationBar(true);
                ((MainFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_CHARTS:
                fragment = new ChartsFragment();
                ((ChartsFragment) fragment).setShouldAddToBackStack(true);
                ((ChartsFragment) fragment).setNeedNavigationBar(true);
                ((ChartsFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_TRANDS:
                fragment = new TrendsFragment();
                ((TrendsFragment) fragment).setShouldAddToBackStack(true);
                ((TrendsFragment) fragment).setNeedNavigationBar(true);
                ((TrendsFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_TEST1:
                fragment = new Test1Fragment();
                break;
            case FRAGMENT_SPLASH:
                fragment = new SplashFragment();
                ((SplashFragment) fragment).setShouldAddToBackStack(false);
                ((SplashFragment) fragment).setNeedToolbar(false);
                ((SplashFragment) fragment).setNeedNavigationBar(false);
                break;
            case FRAGMENT_ALL_ARTISTS:
                fragment = new AllArtistsFragment();
                ((AllArtistsFragment) fragment).setShouldAddToBackStack(true);
                ((AllArtistsFragment) fragment).setNeedNavigationBar(true);
                ((AllArtistsFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_SINGLE_ARTIST:
                fragment = new SingleArtistFragment();
                ((SingleArtistFragment) fragment).setShouldAddToBackStack(true);
                ((SingleArtistFragment) fragment).setNeedNavigationBar(true);
                ((SingleArtistFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_SINGLE_STATION:
                fragment = new SingleStationFragment();
                ((SingleStationFragment) fragment).setShouldAddToBackStack(true);
                ((SingleStationFragment) fragment).setNeedNavigationBar(true);
                ((SingleStationFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_LIBRARY:
                fragment = new LibraryFragment();
                ((LibraryFragment) fragment).setShouldAddToBackStack(true);
                ((LibraryFragment) fragment).setNeedNavigationBar(true);
                ((LibraryFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_SETTINGS:
                fragment = new SettingsFragment();
                ((SettingsFragment) fragment).setShouldAddToBackStack(true);
                ((SettingsFragment) fragment).setNeedNavigationBar(true);
                ((SettingsFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_WELCOME:
                fragment = new WelcomeFragment();
                ((WelcomeFragment) fragment).setShouldAddToBackStack(true);
                ((WelcomeFragment) fragment).setNeedNavigationBar(false);
                ((WelcomeFragment) fragment).setNeedToolbar(false);
                break;
            case FRAGMENT_REGISTER:
                fragment = new RegisterFragment();
                ((RegisterFragment) fragment).setShouldAddToBackStack(true);
                ((RegisterFragment) fragment).setNeedNavigationBar(false);
                ((RegisterFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_LOGIN:
                fragment = new LoginFragment();
                ((LoginFragment) fragment).setShouldAddToBackStack(true);
                ((LoginFragment) fragment).setNeedNavigationBar(false);
                ((LoginFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_REGISTER_STEP_SET_BIRTHDAY_AND_GENDER:
                fragment = new RegisterFragmentSetBirthdayAndGender();
                ((RegisterFragmentSetBirthdayAndGender) fragment).setShouldAddToBackStack(true);
                ((RegisterFragmentSetBirthdayAndGender) fragment).setNeedNavigationBar(false);
                ((RegisterFragmentSetBirthdayAndGender) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_REGISTER_STEP_SET_COUNTRY:
                fragment = new RegisterFragmentStepSetCountry();
                ((RegisterFragmentStepSetCountry) fragment).setShouldAddToBackStack(true);
                ((RegisterFragmentStepSetCountry) fragment).setNeedNavigationBar(false);
                ((RegisterFragmentStepSetCountry) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_REGISTER_SUCCESS:
                fragment = new RegisterFragmentSuccess();
                ((RegisterFragmentSuccess) fragment).setShouldAddToBackStack(true);
                ((RegisterFragmentSuccess) fragment).setNeedNavigationBar(false);
                ((RegisterFragmentSuccess) fragment).setNeedToolbar(false);
                break;
            case FRAGMENT_REGISTER_PRIVACY_POLICY:
                fragment = new RegisterFragmentPrivacyPolicy();
                ((RegisterFragmentPrivacyPolicy) fragment).setShouldAddToBackStack(true);
                ((RegisterFragmentPrivacyPolicy) fragment).setNeedNavigationBar(false);
                ((RegisterFragmentPrivacyPolicy) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_RECOVER_PASSWORD:
                fragment = new PasswordRecoverFragment();
                ((PasswordRecoverFragment) fragment).setShouldAddToBackStack(true);
                ((PasswordRecoverFragment) fragment).setNeedNavigationBar(false);
                ((PasswordRecoverFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_MY_STATIONS:
                fragment = new MyStationsFragment();
                ((MyStationsFragment) fragment).setShouldAddToBackStack(true);
                ((MyStationsFragment) fragment).setNeedNavigationBar(true);
                ((MyStationsFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_RECENT:
                fragment = new RecentFragment();
                ((RecentFragment) fragment).setShouldAddToBackStack(true);
                ((RecentFragment) fragment).setNeedNavigationBar(true);
                ((RecentFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_PLAYLISTS:
                fragment = new PlaylistsFragment();
                break;
            case FRAGMENT_SAVED_TRACKS:
                fragment = new SavedTracksFragment();
                ((SavedTracksFragment) fragment).setShouldAddToBackStack(true);
                ((SavedTracksFragment) fragment).setNeedNavigationBar(true);
                ((SavedTracksFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_SAVED_VIDEOS:
                fragment = new SavedVideosFragment();
                ((SavedVideosFragment) fragment).setShouldAddToBackStack(true);
                ((SavedVideosFragment) fragment).setNeedNavigationBar(true);
                ((SavedVideosFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_CREATE_STATION:
                fragment = new CreateStationFragment();
                ((CreateStationFragment) fragment).setShouldAddToBackStack(true);
                ((CreateStationFragment) fragment).setNeedNavigationBar(true);
                ((CreateStationFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_SUBSCRIPTIONS:
                fragment = new SubscriptionsFragment();
                ((SubscriptionsFragment) fragment).setShouldAddToBackStack(true);
                ((SubscriptionsFragment) fragment).setNeedNavigationBar(true);
                ((SubscriptionsFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_EDIT_PROFILE:
                fragment = new EditProfileFragment();
                ((EditProfileFragment) fragment).setShouldAddToBackStack(true);
                ((EditProfileFragment) fragment).setNeedNavigationBar(true);
                ((EditProfileFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_PASSWORD:
                fragment = new PasswordFragment();
                ((PasswordFragment) fragment).setShouldAddToBackStack(true);
                ((PasswordFragment) fragment).setNeedNavigationBar(true);
                ((PasswordFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_NICKNAME:
                fragment = new NicknameFragment();
                ((NicknameFragment) fragment).setShouldAddToBackStack(true);
                ((NicknameFragment) fragment).setNeedNavigationBar(true);
                ((NicknameFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_SEARCH:
                fragment = new SearchFragment();
                ((SearchFragment) fragment).setShouldAddToBackStack(false);
                ((SearchFragment) fragment).setNeedToolbar(true);
                ((SearchFragment) fragment).setNeedNavigationBar(true);
                break;
            case FRAGMENT_PLAYER:
                fragment = new PlayerFragment();
                ((PlayerFragment) fragment).setShouldAddToBackStack(true);
                ((PlayerFragment) fragment).setNeedToolbar(false);
                ((PlayerFragment) fragment).setNeedNavigationBar(false);
                break;
            case FRAGMENT_GALLERY:
                fragment = new GalleryFragment();
                ((GalleryFragment) fragment).setShouldAddToBackStack(false);
                ((GalleryFragment) fragment).setNeedToolbar(false);
                ((GalleryFragment) fragment).setNeedNavigationBar(false);
                ((GalleryFragment) fragment).setShouldBeSavedInHistory(true);
                break;
            case FRAGMENT_SINGLE_GALLERY_ITEM:
                fragment = new SingleGalleryItemFragment();
                ((SingleGalleryItemFragment) fragment).setShouldAddToBackStack(false);
                ((SingleGalleryItemFragment) fragment).setNeedToolbar(false);
                ((SingleGalleryItemFragment) fragment).setNeedNavigationBar(false);
                break;
            case FRAGMENT_SHARE:
                fragment = new ShareFragment();
                ((ShareFragment) fragment).setShouldAddToBackStack(false);
                ((ShareFragment) fragment).setNeedToolbar(false);
                ((ShareFragment) fragment).setNeedNavigationBar(false);
                break;
            case FRAGMENT_AUTHORIZE:
                fragment = new AuthorizeFragment();
                ((AuthorizeFragment) fragment).setShouldAddToBackStack(false);
                ((AuthorizeFragment) fragment).setNeedToolbar(false);
                ((AuthorizeFragment) fragment).setNeedNavigationBar(false);
                break;
            case FRAGMENT_ALL_VIDEOS:
                fragment = new AllVideosFragment();
                ((AllVideosFragment) fragment).setShouldAddToBackStack(true);
                ((AllVideosFragment) fragment).setNeedNavigationBar(true);
                ((AllVideosFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_ALL_ALBUMS:
                fragment = new AllAlbumsFragment();
                ((AllAlbumsFragment) fragment).setShouldAddToBackStack(true);
                ((AllAlbumsFragment) fragment).setNeedNavigationBar(true);
                ((AllAlbumsFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_ALL_PLAYLISTS:
                fragment = new AllPlaylistsFragment();
                ((AllPlaylistsFragment) fragment).setShouldAddToBackStack(true);
                ((AllPlaylistsFragment) fragment).setNeedNavigationBar(true);
                ((AllPlaylistsFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_ALL_CHARTS:
                fragment = new AllChartsFragment();
                ((AllChartsFragment) fragment).setShouldAddToBackStack(true);
                ((AllChartsFragment) fragment).setNeedNavigationBar(true);
                ((AllChartsFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_ALL_LISTENERS_SET:
                fragment = new AllListenersSetFragment();
                ((AllListenersSetFragment) fragment).setShouldAddToBackStack(true);
                ((AllListenersSetFragment) fragment).setNeedNavigationBar(true);
                ((AllListenersSetFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_ALL_TRACKS:
                fragment = new AllTracksFragment();
                ((AllTracksFragment) fragment).setShouldAddToBackStack(true);
                ((AllTracksFragment) fragment).setNeedNavigationBar(true);
                ((AllTracksFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_ALL_TRENDS_CATEGORIES:
                fragment = new AllCategoriesFragment();
                ((AllCategoriesFragment) fragment).setShouldAddToBackStack(true);
                ((AllCategoriesFragment) fragment).setNeedNavigationBar(true);
                ((AllCategoriesFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_SINGLE_PLAYLIST:
                fragment = new SinglePlaylistFragment();
                ((SinglePlaylistFragment) fragment).setShouldAddToBackStack(true);
                ((SinglePlaylistFragment) fragment).setNeedNavigationBar(true);
                ((SinglePlaylistFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_SURVEY_WELCOME:
                fragment = new FragmentSurveyWelcome();
                ((FragmentSurveyWelcome) fragment).setShouldAddToBackStack(true);
                ((FragmentSurveyWelcome) fragment).setNeedNavigationBar(false);
                ((FragmentSurveyWelcome) fragment).setNeedToolbar(false);
                break;
            case FRAGMENT_SURVEY_CHOOSE_TRANDS:
                fragment = new FragmentSurveyChooseTrands();
                ((FragmentSurveyChooseTrands) fragment).setShouldAddToBackStack(true);
                ((FragmentSurveyChooseTrands) fragment).setNeedToolbar(false);
                ((FragmentSurveyChooseTrands) fragment).setNeedNavigationBar(false);
                break;
            case FRAGMENT_SURVEY_CHOOSE_ARTISTS:
                fragment = new FragmentSurveyChooseArtists();
                ((FragmentSurveyChooseArtists) fragment).setShouldAddToBackStack(true);
                ((FragmentSurveyChooseArtists) fragment).setNeedToolbar(false);
                ((FragmentSurveyChooseArtists) fragment).setNeedNavigationBar(false);
                break;
            case FRAGMENT_SINGLE_ARCHIVE:
                fragment = new SingleArchiveFragment();
                ((SingleArchiveFragment) fragment).setShouldAddToBackStack(true);
                ((SingleArchiveFragment) fragment).setNeedNavigationBar(true);
                ((SingleArchiveFragment) fragment).setNeedToolbar(true);
                break;
            case FRAGMENT_ARCHIVE_ALBUMS:
                fragment = new FragmentArchivesArtists();
                ((FragmentArchivesArtists) fragment).setShouldAddToBackStack(true);
                ((FragmentArchivesArtists) fragment).setNeedNavigationBar(true);
                ((FragmentArchivesArtists) fragment).setNeedToolbar(true);
                return fragment;
            case FRAGMENT_ARCHIVE_TRACKS:
                fragment = new FragmentArchivePlaylists();
                ((FragmentArchivePlaylists) fragment).setShouldAddToBackStack(true);
                ((FragmentArchivePlaylists) fragment).setNeedToolbar(true);
                ((FragmentArchivePlaylists) fragment).setNeedNavigationBar(true);
                return fragment;
            case FRAGMENT_ALL_ARCHIVES:
                fragment = new AllArchivesFragment();
                ((AllArchivesFragment) fragment).setShouldAddToBackStack(true);
                ((AllArchivesFragment) fragment).setNeedNavigationBar(true);
                ((AllArchivesFragment) fragment).setNeedToolbar(true);
                return fragment;
            case FRAGMENT_NO_NETWORK:
                fragment = new NoNetworkFragment();
                ((NoNetworkFragment) fragment).setShouldAddToBackStack(false);
                ((NoNetworkFragment) fragment).setNeedToolbar(false);
                ((NoNetworkFragment) fragment).setNeedNavigationBar(false);
                return fragment;
            case FRAGMENT_FEEDBACK:
                fragment = new FeedbackFragment();
                ((FeedbackFragment) fragment).setShouldAddToBackStack(true);
                ((FeedbackFragment) fragment).setNeedToolbar(true);
                ((FeedbackFragment) fragment).setNeedNavigationBar(true);
                return fragment;
            case FRAGMENT_FOLLOW_YOU:
                fragment = new FollowYouFragment();
                ((FollowYouFragment) fragment).setShouldAddToBackStack(true);
                ((FollowYouFragment) fragment).setNeedNavigationBar(false);
                ((FollowYouFragment) fragment).setNeedToolbar(true);
                return fragment;
            case FRAGMENT_CHOOSE_PLAYLIST:
                fragment = new ChoosePlaylistFragment();
                ((ChoosePlaylistFragment) fragment).setShouldAddToBackStack(true);
                ((ChoosePlaylistFragment) fragment).setNeedNavigationBar(true);
                ((ChoosePlaylistFragment) fragment).setNeedToolbar(true);
                return fragment;
            case FRAGMENT_CREATE_PLAYLIST:
                fragment = new PlaylistCreateFragment();
                ((PlaylistCreateFragment) fragment).setShouldAddToBackStack(true);
                ((PlaylistCreateFragment) fragment).setNeedNavigationBar(true);
                ((PlaylistCreateFragment) fragment).setNeedToolbar(true);
                return fragment;
            case FRAGMENT_ALL_STREAMS:
                fragment = new AllStreamFragment();
                ((AllStreamFragment) fragment).setShouldAddToBackStack(true);
                ((AllStreamFragment) fragment).setNeedNavigationBar(true);
                ((AllStreamFragment) fragment).setNeedToolbar(true);
                return fragment;
            case FRAGMENT_ALL_STREAMS_CATEGORIES:
                fragment = new AllStreamsCategoriesFragment();
                ((AllStreamsCategoriesFragment) fragment).setShouldAddToBackStack(true);
                ((AllStreamsCategoriesFragment) fragment).setNeedToolbar(true);
                ((AllStreamsCategoriesFragment) fragment).setNeedNavigationBar(true);
                return fragment;
            case FRAGMENT_ALL_TOP_PLAYLISTS_MAIN_PAGE:
                fragment = new AllTopPlaylistsFragmentMainPage();
                ((AllTopPlaylistsFragmentMainPage) fragment).setShouldAddToBackStack(true);
                ((AllTopPlaylistsFragmentMainPage) fragment).setNeedNavigationBar(true);
                ((AllTopPlaylistsFragmentMainPage) fragment).setNeedToolbar(true);
                return fragment;
            case FRAGMENT_ALL_TOP_PLAYLISTS_STATION:
                fragment =  new AllTopPlaylistsFromStationFragment();
                ((AllTopPlaylistsFromStationFragment) fragment).setNeedToolbar(true);
                ((AllTopPlaylistsFromStationFragment) fragment).setShouldAddToBackStack(true);
                ((AllTopPlaylistsFromStationFragment) fragment).setNeedNavigationBar(true);
                return fragment;
            case FRAGMENT_TRACK_SUCCESSFULLY_ADDED:
                fragment = new TrackAddedSuccessfullyFragment();
                ((TrackAddedSuccessfullyFragment) fragment).setNeedNavigationBar(false);
                ((TrackAddedSuccessfullyFragment) fragment).setNeedToolbar(false);
                ((TrackAddedSuccessfullyFragment) fragment).setShouldAddToBackStack(false);
                return fragment;
            case FRAGMENT_TRACK_ALREADY_EXIST:
                fragment = new TrackAlreadyAddedFragment();
                ((TrackAlreadyAddedFragment) fragment).setShouldAddToBackStack(false);
                ((TrackAlreadyAddedFragment) fragment).setNeedToolbar(false);
                ((TrackAlreadyAddedFragment) fragment).setNeedNavigationBar(false);
                break;
        }
        return fragment;
    }

    public DialogFragment returnMenuFragmentById(int menuType) {
        DialogFragment fragment = new MenuDialogFragment();
//    ((MenuFragment) fragment).setShouldAddToBackStack(false);
//    ((MenuFragment) fragment).setNeedNavigationBar(false);
//    ((MenuFragment) fragment).setNeedToolbar(false);
        Bundle bundle = new Bundle();
        bundle.putInt(Const.FragmentArguments.MENU_TYPE, menuType);
        fragment.setArguments(bundle);
        return fragment;
    }

    public int detectParentGroup(int key) {
        switch (key) {
            case Const.FragmentKeyStack.MAIN_FRAGMENT_SCREEN_KEY:
                return R.id.action_main;
            case Const.FragmentKeyStack.TRANDS_FRAGMENT_SCREEN_KEY:
                return R.id.action_trands;
            case Const.FragmentKeyStack.CHARTS_FRAGMENT_SCREEN_KEY:
                return R.id.action_charts;
            case Const.FragmentKeyStack.LIBRARY_FRAGMENT_SCREEN_KEY:
                return R.id.action_library;
        }
        return 0;
    }

    public void clearFragmentStack(int stackId) {
        customFragmentManagerMap.remove(stackId);
    }

    // UNIQUE FLOW

    private void deleteArtistAndDisplayFragment(Fragment fragment, int screenKey) {
        artistUseCase.deleteArtist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    customFragmentManagerListener.displayFragmentListener(fragment);
                    customFragmentManagerListener.setSelectedBottomItemListener(detectParentGroup(screenKey));
                }, throwable -> {
                    Log.e("mLog", "errorHandled while deleting artist  from database");
                    throwable.printStackTrace();
                    customFragmentManagerMap.remove(screenKey);
                    Stack<Fragment> fragmentStack = new Stack();
                    Fragment fragmentNew = returnFragmentById(Const.CURRENT_SCREEN_KEY);
                    fragmentStack.add(fragmentNew);
                    customFragmentManagerMap.put(screenKey, fragmentStack);
                    customFragmentManagerListener.displayFragmentListener(fragmentNew);
                    customFragmentManagerListener.setSelectedBottomItemListener(detectParentGroup(screenKey));
                });
    }

    public Fragment findSpecificFragmentAndRemove(int fragmentId) {
        Fragment requiredFragment = returnFragmentById(fragmentId);
        for (int i = 0; i < customFragmentManagerMap.size(); i++) {
            Stack<Fragment> fragmentsStack = (Stack<Fragment>) customFragmentManagerMap.values().toArray()[i];
            for (int j = 0; j < fragmentsStack.size(); j++) {
                Fragment fragment = ((Stack<Fragment>) customFragmentManagerMap.values().toArray()[i]).get(j);
                if(fragment != null) {
                    if (fragment.getClass().toString().equals(requiredFragment.getClass().toString())) {
                        ((Stack<Fragment>) customFragmentManagerMap.values().toArray()[i]).remove(fragment);
                        //  fragmentThatWasFound = fragment;
                        return fragment;
                    }
                }
            }
        }
        return null;
    }

    public void removeAllFragmentsInStack(int stackId){
        customFragmentManagerMap.get(stackId).clear();
    }


    public boolean getIsEmpty() {
        return customFragmentManagerMap.isEmpty();
    }

    public boolean isFragmentPresentInStack(int keyStack, int fragmentKey) {
        Stack<Fragment> fragments = customFragmentManagerMap.get(keyStack);
        Fragment desiredFragment = returnFragmentById(fragmentKey);
        if (fragments == null)
            return false;
        if (fragments.size() == 0)
            return false;
        for (Fragment existingFragment : fragments) {
            if (desiredFragment.getClass().toString().equals(existingFragment.getClass().toString()))
                return true;
        }
        return false;
    }

    public void displayStackContent() {
        String localTag = "customFragmentManager";
        for (int i = 0; i < customFragmentManagerMap.size(); i++) {
            Stack<Fragment> fragmentsStack = (Stack<Fragment>) customFragmentManagerMap.values().toArray()[i];
            Integer fragmentsStackKeys = (Integer) customFragmentManagerMap.keySet().toArray()[i];
            Log.d(localTag, "stackId: " + fragmentsStackKeys);
            for (int j = 0; j < fragmentsStack.size(); j++) {
                Fragment fragment = ((Stack<Fragment>) customFragmentManagerMap.values().toArray()[i]).get(j);
                if (fragment != null)
                    Log.d(localTag, "fragmentId: " + fragment.getClass().toString());
            }
        }
    }
}
